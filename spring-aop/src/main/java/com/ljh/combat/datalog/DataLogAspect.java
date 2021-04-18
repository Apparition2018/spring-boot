package com.ljh.combat.datalog;

import com.ljh.combat.domain.Action;
import com.ljh.combat.domain.ActionType;
import com.ljh.combat.domain.ChangeItem;
import com.ljh.combat.util.DiffUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * DataLogAspect
 *
 * @author Arsenal
 * created on 2020/1/3 1:22
 */
@Aspect
@Component
public class DataLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(DataLogAspect.class);

    private final ActionDao actionDao;

    @Autowired
    public DataLogAspect(ActionDao actionDao) {
        this.actionDao = actionDao;
    }

    @Pointcut("execution(public * com.ljh.combat.dao.*.save*(..))")
    public void save() {

    }

    @Pointcut("execution(public * com.ljh.combat.dao.*.deleteById*(..))")
    public void deleteById() {

    }

    /**
     * 1)判断是什么类型的操作，增加、删除、还是更新
     *  增加/更新 save(Product)，通过 id 区分是增加还是更新
     *  删除 delete(id)
     * 2)获取 change item
     *  (1)新增操作，before 直接获取，after 记录下新增之后的 id
     *  (2)更新操作，before 获取操作之前的记录，after 获取操作之后的记录，然后 diff
     *  (3)删除操作，before 根据 id 取记录
     * 3)保存操作记录
     *  actionType
     *  objectId
     *  objectClass
     */
    @Around("save() || deleteById()")
    public Object addOperateLog(ProceedingJoinPoint joinPoint) {
        Object returnObj = null;
        // TODO BEFORE OPERATION init action
        String method = joinPoint.getSignature().getName();
        ActionType actionType = null;
        Action action = new Action();
        Long id = null;
        Object oldObj = null;
        try {
            if ("save".equals(method)) {
                // insert or update
                Object obj = joinPoint.getArgs()[0];
                try {
                    id = (Long) PropertyUtils.getProperty(obj, "id");
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                if (id == null) {
                    actionType = ActionType.INSERT;
                    List<ChangeItem> changeItems = DiffUtil.getInsertChangeItems(obj);
                    action.getChanges().addAll(changeItems);
                    action.setObjectClass(obj.getClass().getName());
                } else {
                    actionType = ActionType.UPDATE;
                    action.setObjectId(id);
                    oldObj = DiffUtil.getObjectById(joinPoint.getTarget(), id);
                    action.setObjectClass(oldObj.getClass().getName());
                }
            } else if ("deleteById".equals(method)) {
                id = Long.valueOf(joinPoint.getArgs()[0].toString());
                actionType = ActionType.DELETE;
                oldObj = DiffUtil.getObjectById(joinPoint.getTarget(), id);
                ChangeItem changeItem = DiffUtil.getDeleteChangeItem(oldObj);
                action.getChanges().add(changeItem);
                action.setObjectId(id);
                action.setObjectClass(oldObj.getClass().getName());
            }
            returnObj = joinPoint.proceed(joinPoint.getArgs());
            // TODO AFTER OPERATION save action
            action.setActionType(actionType);
            if (ActionType.INSERT == actionType) {
                // new id
                Object newId = PropertyUtils.getProperty(returnObj, "id");
                action.setObjectId(Long.valueOf(newId.toString()));
            } else if (ActionType.UPDATE == actionType) {
                Object newObj = DiffUtil.getObjectById(joinPoint.getTarget(), id);
                List<ChangeItem> changeItems = DiffUtil.getChangeItems(oldObj, newObj);
                action.getChanges().addAll(changeItems);
            }
            action.setOperator("admin"); // TODO dynamic get from ThreadLocal / Session
            actionDao.save(action);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
        return returnObj;
    }

}

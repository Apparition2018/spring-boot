package com.imooc.xiang.dao;

import com.imooc.xiang.entity.Area;

import java.util.List;

public interface AreaDao {

    /**
     * 列出区域列表
     * @return areaList
     */
    List<Area> queryArea();

    /**
     * 根据Id列出具体区域
     * @param areaId 区域Id
     * @return area
     */
    Area queryAreaById(int areaId);

    /**
     * 插入区域信息
     * @param area area
     * @return 1
     */
    int insertArea(Area area);

    /**
     * 更新区域信息
     * @param area area
     * @return 1
     */
    int updateArea(Area area);

    /**
     * 删除区域信息
     * @param areaId 区域Id
     * @return 1
     */
    int deleteArea(int areaId);
}

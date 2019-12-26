package com.imooc.xiang.service;

import com.imooc.xiang.entity.Area;

import java.util.List;

public interface AreaService {
    /**
     * 获取区域列表
     * @return areaList
     */
    List<Area> getAreaList();

    /**
     * 通过区域Id获取区域信息
     * @param areaId 区域Id
     * @return area
     */
    Area getAreaById(int areaId);

    /**
     * 增加区域信息
     * @param area area
     * @return 1
     */
    boolean addArea(Area area);

    /**
     * 修改区域信息
     * @param area area
     * @return 1
     */
    boolean modifyArea(Area area);

    /**
     * 删除区域信息
     * @param areaId 区域Id
     * @return 1
     */
    boolean deleteArea(int areaId);
}

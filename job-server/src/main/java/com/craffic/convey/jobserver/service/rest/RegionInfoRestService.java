package com.craffic.convey.jobserver.service.rest;

import com.alibaba.dubbo.config.annotation.Service;
import com.craffic.convey.jobapi.api.CascaderDataInterface;
import com.craffic.convey.jobapi.vo.NodeVo;
import com.craffic.convey.jobapi.vo.SysCityVo;
import com.craffic.convey.jobapi.vo.SysProvinceVo;
import com.craffic.convey.jobserver.dao.SysAreaMapper;
import com.craffic.convey.jobserver.dao.SysCityMapper;
import com.craffic.convey.jobserver.dao.SysProvinceMapper;
import com.craffic.convey.jobserver.model.SysArea;
import com.craffic.convey.jobserver.model.SysCity;
import com.craffic.convey.jobserver.model.SysProvince;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegionInfoRestService implements CascaderDataInterface {

    @Autowired
    private SysProvinceMapper provinceMapper;
    @Autowired
    private SysCityMapper cityMapper;
    @Autowired
    private SysAreaMapper areaMapper;

    @Override
    public List<NodeVo> queryRegionCascaderData() {
        // 查询省份列表
        List<SysProvince> provinceList = provinceMapper.getAllProvinceList();
        if (CollectionUtils.isEmpty(provinceList)) {
            return new ArrayList<>();
        }
        List<NodeVo> regionData = new ArrayList<>();
        for (SysProvince province : provinceList) {
            Long provinceId = province.getProvinceId();
            String provinceName = province.getProvinceName();
            NodeVo node = new NodeVo();
            node.setValue(provinceId.toString());
            node.setLabel(provinceName);
            // 城市
            List<SysCity> cityList = cityMapper.getCityByProvince(provinceId);
            List<NodeVo> tmpCityList = new ArrayList();
            if (CollectionUtils.isEmpty(cityList)) {
                node.setChildren(new ArrayList());
            }
            for (SysCity city : cityList) {
                Long cityId = city.getCityId();
                String cityName = city.getCityName();
                NodeVo cityNode = new NodeVo();
                cityNode.setValue(cityId.toString());
                cityNode.setLabel(cityName);

                // 县区
                List<NodeVo> tmpAreaList = new ArrayList<>();
                List<SysArea> areasByCity = areaMapper.getAreasByCity(cityId);
                if (CollectionUtils.isEmpty(areasByCity)) {
                    cityNode.setChildren(new ArrayList<>());
                }
                for (SysArea area : areasByCity) {
                    Long areaId = area.getAreaId();
                    String areaName = area.getAreaName();
                    NodeVo areaNode = new NodeVo();
                    areaNode.setValue(areaId.toString());
                    areaNode.setLabel(areaName);
                    tmpAreaList.add(areaNode);
                }
                cityNode.setChildren(tmpAreaList);
                tmpCityList.add(cityNode);
            }
            node.setChildren(tmpCityList);
            regionData.add(node);


        }

        return regionData;
    }
}

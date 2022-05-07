package com.craffic.convey.jobserver.service.rest;

import com.alibaba.dubbo.config.annotation.Service;
import com.craffic.convey.jobapi.api.CascaderDataInterface;
import com.craffic.convey.jobapi.vo.NodeVo;
import com.craffic.convey.jobapi.vo.SysCityVo;
import com.craffic.convey.jobapi.vo.SysProvinceVo;
import com.craffic.convey.jobserver.dao.OaDictMapper;
import com.craffic.convey.jobserver.dao.SysAreaMapper;
import com.craffic.convey.jobserver.dao.SysCityMapper;
import com.craffic.convey.jobserver.dao.SysProvinceMapper;
import com.craffic.convey.jobserver.model.OaDict;
import com.craffic.convey.jobserver.model.SysArea;
import com.craffic.convey.jobserver.model.SysCity;
import com.craffic.convey.jobserver.model.SysProvince;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionInfoRestService implements CascaderDataInterface {

    public static final String PROFESSIONAL = "PROFESSIONAL";

    @Autowired
    private SysProvinceMapper provinceMapper;
    @Autowired
    private SysCityMapper cityMapper;
    @Autowired
    private SysAreaMapper areaMapper;
    @Autowired
    private OaDictMapper dictMapper;

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

    @Override
    public List<NodeVo> queryProfessionCascaderData() {
        // 查询省份列表
        List<OaDict> allDictByName = dictMapper.getAllDictByName(PROFESSIONAL);
        List<OaDict> parentProfession = allDictByName.stream().filter(item -> item.getpKey() == null).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(parentProfession)) {
            return new ArrayList();
        }
        List<NodeVo> professionList = new ArrayList<>();
        for (OaDict dict : parentProfession) {
            Long key = dict.getKey();
            String value = dict.getValue();
            NodeVo nodeVo = new NodeVo();
            nodeVo.setValue(key.toString());
            nodeVo.setLabel(value);
            // 子节点
            List<OaDict> subNodeList = dictMapper.getDictsPkey(key);
            if (CollectionUtils.isEmpty(subNodeList)) {
                nodeVo.setChildren(new ArrayList());
            }
            List<NodeVo> tempSubList = new ArrayList();
            for (OaDict subNode : subNodeList) {
                Long subKey = subNode.getKey();
                String subValue = subNode.getValue();
                NodeVo subNodeVo = new NodeVo();
                subNodeVo.setValue(subKey.toString());
                subNodeVo.setLabel(subValue);
                tempSubList.add(subNodeVo);
            }
            nodeVo.setChildren(tempSubList);
            professionList.add(nodeVo);
        }
        return professionList;
    }


}

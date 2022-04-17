package com.craffic.convey.server.service;

import com.craffic.convey.server.dao.CvDeptMapper;
import com.craffic.convey.server.model.CvDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CvDeptService {

    @Autowired
    private CvDeptMapper deptMapper;

    public List<CvDept> queryAllDepartments() {
        return deptMapper.queryAllDeptByParentId(-1);
    }

    public List<CvDept> queryAllSubNodeById(Integer deptId) {
        return deptMapper.queryAllDeptByParentId(deptId);
    }

    /**
     * 添加部门
     * @param dept
     * @return
     */
    @Transactional
    public CvDept addDept(CvDept dept) {
        // 查看当前节点对象
        CvDept parentNode = deptMapper.queryNodeById(dept.getParentId());
        parentNode.setIsParent(true);
        parentNode.setEnabled(true);
        // 更新父节点
        Integer parentUpdateFlag = deptMapper.updateDept(parentNode);
        if (parentUpdateFlag != 1) {
            return null;
        }
        // 查询当前部门表最大id
        Integer deptId = deptMapper.selectMaxId();
        // 构建新建部门
        CvDept newDept = new CvDept();
        int id = deptId + 1;
        newDept.setId(id);
        newDept.setName(dept.getName());
        newDept.setParentId(dept.getParentId());
        newDept.setDeptPath(parentNode.getDeptPath() + "." + id);
        newDept.setEnabled(true);
        newDept.setIsParent(false);
        deptMapper.addDept(newDept);
        // build result dept
        newDept.setDeptPath(null);
        newDept.setEnabled(null);
        newDept.setIsParent(null);
        newDept.setChildren(new ArrayList<>());
        return newDept;
    }

    /**
     * 删除部门
     */
    @Transactional
    public Boolean deleteDept(Integer deptId){
        // 查询当前节点是否父节点
        CvDept cvDept = deptMapper.queryNodeById(deptId);
        if (cvDept.getIsParent()) {
           // 如果是父节点，查询当前结点及下属所有子节点
            List<CvDept> subNodes = queryAllSubNodeById(cvDept.getId());
            subNodes.add(cvDept);
            if (!CollectionUtils.isEmpty(subNodes)) {
                List<Integer> childrenList = new ArrayList<>();
                List<Integer> allChildrenId = getAllChildrenByList(subNodes, childrenList);
                Integer count = deleteDeptsByList(allChildrenId);
                if (count == allChildrenId.size()) {
                    return true;
                }
                return false;
            }
            return false;
        }
        // 如果不是父节点，而是一个叶子节点，则删除当前结点即可
        List<Integer> idList = new ArrayList<>();
        idList.add(deptId);
        Integer count = deleteDeptsByList(idList);
        if (count == 1) {
            return true;
        }
        return false;

    }

    public Integer deleteDeptsByList(List<Integer> deptIdList){
        return deptMapper.deleteByList(deptIdList);
    }

    /**
     * 递归查询所有子节点id
     * @param deptList
     * @return
     */
    private List<Integer> getAllChildrenByList(List<CvDept> deptList, List<Integer> childrenList){
        if (CollectionUtils.isEmpty(deptList)) {
            return new ArrayList<>();
        }
        for (CvDept dept : deptList) {
            childrenList.add(dept.getId());
            if (dept.getIsParent()) {
                List<CvDept> children = dept.getChildren();
                getAllChildrenByList(children, childrenList);
            }
        }
        return childrenList;
    }
}

package com.laoxu.demo.service.impl;

import com.laoxu.demo.dataobject.entity.WorkerDO;
import com.laoxu.demo.mapper.WorkerMapper;
import com.laoxu.demo.service.WorkerService;
import com.laoxu.demo.util.DbUtils;
import com.laoxu.demo.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public WorkerDO queryById(Integer id) {
        Example.Builder builder = DbUtils.newExampleBuilder(WorkerDO.class);
        DbUtils.setEqualToProp(builder, WorkerDO.PROP_ID, id);
        return workerMapper.selectOneByExample(builder.build());
    }

    @Override
    public void insert(WorkerDO worker) {
        workerMapper.insertSelective(worker);
    }

    @Override
    public void update(WorkerDO worker) {
        workerMapper.updateByPrimaryKeySelective(worker);
    }

    @Override
    public void deleteById(Integer id) {
        workerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<WorkerDO> queryByName(String name) {
        Example.Builder builder = DbUtils.newExampleBuilder(WorkerDO.class);
        DbUtils.setEqualToProp(builder, WorkerDO.PROP_NAME, name);
        return workerMapper.selectByExample(builder.build());
    }

    @Override
    public void batchUpdate(String name) {
        Example.Builder builder = DbUtils.newExampleBuilder(WorkerDO.class);
        DbUtils.setEqualToProp(builder, WorkerDO.PROP_NAME, name);
        List<WorkerDO> workerDOList = workerMapper.selectByExample(builder.build());
        for (WorkerDO workerDO : workerDOList) {
            workerDO.setName("王五");
        }
        workerMapper.updateBatchByPrimaryKeySelective(workerDOList);
    }

    @Override
    public void batchInsert(List<WorkerDO> workerDOList) {
        workerMapper.insertList(workerDOList);
    }

    @Override
    public List<WorkerDO> queryAll() {
        return workerMapper.selectAll();
    }

    @Override
    public Set<String> workerNameSet() {
        List<WorkerDO> workerDOList = workerMapper.selectAll();
        Set<String> nameSet = StreamUtils.ofNullable(workerDOList).map(WorkerDO::getName).collect(Collectors.toSet());
        return nameSet;
    }

    @Override
    public Map<Integer, String> idNameMap() {
        List<WorkerDO> workerDOList = workerMapper.selectAll();
        Map<Integer, String> idNameMap = StreamUtils
                .ofNullable(workerDOList)
                .collect(Collectors.toMap(
                        WorkerDO::getId,
                        WorkerDO::getName
                ));
        return idNameMap;
    }

    @Override
    public void deleteByName(String name) {
        Example.Builder builder = DbUtils.newExampleBuilder(WorkerDO.class);
        DbUtils.setEqualToProp(builder, WorkerDO.PROP_NAME, name);
        workerMapper.deleteByExample(builder.build());
    }
}

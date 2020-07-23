package com.laoxu.demo.service;

import com.laoxu.demo.dataobject.entity.WorkerDO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * (Worker)表服务接口
 *
 * @author xsj
 * @since 2020-07-22 10:02:33
 */
public interface WorkerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WorkerDO queryById(Integer id);

    /**
     * 新增数据
     *
     * @param worker 实例对象
     * @return 实例对象
     */
    void insert(WorkerDO worker);

    /**
     * 修改数据
     *
     * @param worker 实例对象
     * @return 实例对象
     */
    void update(WorkerDO worker);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    void deleteById(Integer id);

    List<WorkerDO> queryByName(String name);

    void batchUpdate(String name);

    void batchInsert(List<WorkerDO> workerDOList);

    List<WorkerDO> queryAll();

    Set<String> workerNameSet();

    Map<Integer,String> idNameMap();

    void deleteByName(String name);

}
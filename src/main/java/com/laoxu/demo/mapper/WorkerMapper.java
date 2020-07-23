package com.laoxu.demo.mapper;

import com.laoxu.demo.dataobject.entity.WorkerDO;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface WorkerMapper extends Mapper<WorkerDO>,
        MySqlMapper<WorkerDO>, BatchMapper<WorkerDO> {


}

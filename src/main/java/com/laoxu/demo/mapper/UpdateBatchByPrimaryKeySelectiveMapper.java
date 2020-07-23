/**
 * UpdateBatchByPrimaryKeySelectiveMapper.java
 * <p>
 * Copyright © 2020 Hangzhou Zhuo Jian Mdt InfoTech Ltd
 * </p>
 * All rights reserved
 * @Date: 2020/7/16 11:59 Created
 * @Author: Technical team of advice
 * @ProjectName: advice
 */
package com.laoxu.demo.mapper;

import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * @ClassName UpdateBatchByPrimaryKeySelectiveMapper
 * @Description 批量更新
 * @author 余昌辉
 * @date Created in 11:59 2020/7/16.
 */
@tk.mybatis.mapper.annotation.RegisterMapper
public interface UpdateBatchByPrimaryKeySelectiveMapper<T> {

    /**
     * 根据Example条件批量更新实体`record`包含的不是null的属性值
     * @author 余昌辉
     * @date 2020/7/16 13:47
     */
    @UpdateProvider(type = BatchExampleProvider.class, method = "dynamicSQL")
    int updateBatchByPrimaryKeySelective(List<? extends T> recordList);
}

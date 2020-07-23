/**
 * BatchMapper.java
 * <p>
 * Copyright © 2020 Hangzhou Zhuo Jian Mdt InfoTech Ltd
 * </p>
 * All rights reserved
 * @Date: 2020/7/16 14:32 Created
 * @Author: Technical team of advice
 * @ProjectName: advice
 */
package com.laoxu.demo.mapper;

/**
 * @ClassName BatchMapper
 * @Description 批量操作接口
 * @author 余昌辉
 * @date Created in 14:32 2020/7/16.
 */
@tk.mybatis.mapper.annotation.RegisterMapper
public interface BatchMapper<T> extends UpdateBatchByPrimaryKeySelectiveMapper<T> {
}

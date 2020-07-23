package com.laoxu.demo.common.model;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @Author: YLBG-LDH-1506
 * @Description:
 * @Date: 2017/07/30
 */
@MappedSuperclass
public abstract class BaseObject {

    /**
     * 非主键id的默认值，为了可以走索引比判断null效率高
     */
    public static final Long DEFAULT_ID = -1l;
    /**
     * 未删除的时候的deleteTime
     */
    public static final String VAL_NOT_DELETED = "";

    /**
     * 创建时间 表字段 : create_time
     */
    public static final String PROP_CREATE_TIME = "createTime";
    private Date createTime;

    /**
     * 修改时间 表字段 : update_time
     */
    public static final String PROP_UPDATE_TIME = "updateTime";
    private Date updateTime;

    /**
     * 删除时间 表字段 : delete_time
     */
    public static final String PROP_DELETE_TIME = "deleteTime";
    private String deleteTime;

    /**
     * 获取 创建时间 字段:create_time
     * @return create_time, 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 创建时间 字段:create_time
     * @param createTime the value for create_time, 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 修改时间 字段:update_time
     * @return update_time, 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 修改时间 字段:update_time
     * @param updateTime the value for update_time, 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取 删除时间 字段:delete_time
     * @return delete_time, 删除时间
     */
    public String getDeleteTime() {
        return deleteTime;
    }

    /**
     * 设置 删除时间 字段:delete_time
     * @param deleteTime the value for delete_time, 删除时间
     */
    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }
}

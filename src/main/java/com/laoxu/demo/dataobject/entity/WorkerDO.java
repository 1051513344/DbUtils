package com.laoxu.demo.dataobject.entity;

import com.laoxu.demo.common.model.BaseObject;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "worker")
public class WorkerDO extends BaseObject implements Serializable {

    public static final String PROP_ID = "id";
    @Id
    private Integer id;
    public static final String PROP_WORK_ID = "workId";
    private Integer workId;
    public static final String PROP_NAME = "name";
    private String name;
    public static final String PROP_SEX = "sex";
    private String sex;
    public static final String PROP_DEPARTMENT = "department";
    private String department;
    public static final String PROP_AGE = "age";
    private Integer age;

    private static final long serialVersionUID = -7274956400697762718L;

}

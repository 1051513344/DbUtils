package com.laoxu.demo.aop;


import com.laoxu.demo.common.model.BaseObject;
import com.laoxu.demo.util.DateUtils;
import com.laoxu.demo.util.ReflectUtils;
import com.laoxu.demo.util.StreamUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.binding.MapperProxy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper;
import tk.mybatis.mapper.common.example.UpdateByExampleSelectiveMapper;

import javax.annotation.PostConstruct;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * @Author: YLBG-LDH-1506
 * @Description:自动注入其内容，不对{@link tk.mybatis.mapper.common.base.delete.DeleteMapper}进行处理
 * @Date: 2017/08/09
 */
@Aspect
@Component
@Slf4j
public class MapperAspect {

    private static SimpleTypeConverter simpleTypeConverter = new SimpleTypeConverter();

    @PostConstruct
    private void init() {
       log.info("MapperAspect start!");
    }

    @Before(value = "execution(* tk.mybatis.mapper.common.base.insert.InsertMapper.insert(java.lang.Object))&& args(record)")
    public void insertMapperBeforeHandle(Object record) {
        setCreateTime(record);
    }

    @Before(value = "execution(* tk.mybatis.mapper.common.special.InsertListMapper.insertList(java.util.List))&& args(list)")
    public void insertSelectiveMapperBeforeHandle(List list) {
        setCreateTimeList(list);
    }

    @Before(value = "execution(* tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper.insertSelective(java.lang.Object))&& args(record)")
    public void insertSelectiveMapperBeforeHandle(Object record) {
        setCreateTime(record);
    }

    @Before(value = "execution(* tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeyMapper.updateByPrimaryKey(java.lang.Object))&& args(record)")
    public void updateByPrimaryKeyMapperBeforeHandle(Object record) {
        setUpdateTime(record);
    }

    @Before(value = "execution(* tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper.updateByPrimaryKeySelective(java.lang.Object))&& args(record)")
    public void updateByPrimaryKeySelectiveMapperBeforeHandle(Object record) {
        setUpdateTime(record);
    }

    @Before(value = "execution(* tk.mybatis.mapper.common.example.UpdateByExampleMapper.updateByExample(java.lang.Object,java.lang.Object))&& args(record,example)")
    public void updateByExampleMapperBeforeHandle(Object record,Object example) {
        setUpdateTime(record);
    }

    @Before(value = "execution(* tk.mybatis.mapper.common.example.UpdateByExampleSelectiveMapper.updateByExampleSelective(java.lang.Object,java.lang.Object))&& args(record,example)")
    public void updateByExampleSelectiveMapperBeforeHandle(Object record,Object example) {
        setUpdateTime(record);
    }

    @Around(value = "execution(* tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper.deleteByPrimaryKey(java.lang.Object))&& args(key)")
    public Object deleteByPrimaryKeyMapperAroundHandle(ProceedingJoinPoint joinPoint, Object key)
            throws Exception {
        Object target = joinPoint.getTarget();
        Class bClass = getBaseObjectClass(target);
        // 如果泛型是BaseObject类型
        if (target instanceof UpdateByPrimaryKeySelectiveMapper && null != bClass
                && ClassUtils.isAssignable(bClass, BaseObject.class)) {
            BaseObject baseObject = (BaseObject) bClass.newInstance();
            // 设置删除
            baseObject.setDeleteTime(DateUtils.getDeleteTime());
            UpdateByPrimaryKeySelectiveMapper mapper = (UpdateByPrimaryKeySelectiveMapper) target;
            // 设置主键
            setPrimaryKeyValue(baseObject, key);
            // 更新
            return mapper.updateByPrimaryKeySelective(baseObject);
        }
        return 0;
    }

    @Around(value = "execution(* tk.mybatis.mapper.common.example.DeleteByExampleMapper.deleteByExample(java.lang.Object))&& args(example)")
    public Object deleteByExampleMapperAroundHandle(ProceedingJoinPoint joinPoint, Object example)
            throws Exception {
        Object target = joinPoint.getTarget();
        Class bClass = getBaseObjectClass(target);
        // 如果泛型是BaseObject类型
        if (target instanceof UpdateByExampleSelectiveMapper && null != bClass
                && ClassUtils.isAssignable(bClass, BaseObject.class)) {
            BaseObject baseObject = (BaseObject) bClass.newInstance();
            // 设置删除
            baseObject.setDeleteTime(DateUtils.getDeleteTime());
            UpdateByExampleSelectiveMapper mapper = (UpdateByExampleSelectiveMapper) target;
            // 更新
            return mapper.updateByExampleSelective(baseObject, example);
        }
        return 0;
    }

    private Class getBaseObjectClass(Object mapper) throws Exception {
        Class mapperClass = getRawMapperClass(mapper);
        Type[] types = mapperClass.getGenericInterfaces();
        for (int i = 0; i < types.length; i++) {
            Type type = types[i];
            if (type instanceof ParameterizedType) {
                ParameterizedType pType = (ParameterizedType) type;
                if (StringUtils.contains(type.getTypeName(), "Mapper<")) {
                    return ClassUtils.getClass(pType.getActualTypeArguments()[0].getTypeName());
                }
            }
        }
        return null;
    }

    private void setUpdateTime(Object record) {
        if (record instanceof BaseObject) {
            BaseObject object = (BaseObject) record;
            object.setUpdateTime(new Date());
        }
    }
    private void setCreateTimeList(List list) {
        StreamUtils.ofNullable(list).forEach(record -> {
            if (record instanceof BaseObject) {
                BaseObject object = (BaseObject) record;
                object.setCreateTime(new Date());
                object.setDeleteTime(BaseObject.VAL_NOT_DELETED);
            }
        });
    }

    private void setCreateTime(Object record) {
        if (record instanceof BaseObject) {
            BaseObject object = (BaseObject) record;
            object.setCreateTime(new Date());
            object.setDeleteTime(BaseObject.VAL_NOT_DELETED);
        }
    }

    private Class getRawMapperClass(Object mapper) throws Exception {
        Object rawMapper = ReflectUtils.getTargetDeep(mapper);
        if (rawMapper instanceof MapperProxy) {
            MapperProxy mapperProxy = (MapperProxy) rawMapper;
            Field field = ReflectionUtils.findField(MapperProxy.class, "mapperInterface");
            return (Class) ReflectUtils.getFieldForce(field, mapperProxy);
        }
        return rawMapper.getClass();
    }

    public static void setPrimaryKeyValue(Object obj, Object primaryKey) {
        if (null == obj) {
            return;
        }
        ReflectionUtils.doWithFields(obj.getClass(), field -> {
            if (field.isAnnotationPresent(Id.class)) {
                if (!Modifier.isPublic(field.getModifiers()) || !field.isAccessible()) {
                    field.setAccessible(true);
                }
                field.set(obj, simpleTypeConverter.convertIfNecessary(primaryKey, field.getType()));
            }
        });
    }

}

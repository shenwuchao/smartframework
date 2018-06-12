package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类
 * @author wangc
 * @since 1.0.0
 */
public final class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 利用无参构造函数创建对象
     *
     * @param cls
     * @return
     */
    public static Object createInstance (Class<?> cls) {
        Object obj = null;
        try {
            obj = cls.newInstance();
            LOGGER.info("new instance is success");
        } catch (InstantiationException e) {
            e.printStackTrace();
            LOGGER.error("new instance is InstantiationException", e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LOGGER.error("new instance is IllegalAccessException", e);
        }
        return obj;
    }
    public static Object createInstance (Class<?> cls, Class<?>[] parameterTypes, Object...parameters) {
        Object obj = null;
        try {
            Constructor constructor = cls.getDeclaredConstructor(parameterTypes);
            constructor.setAccessible(true);
            obj = constructor.newInstance(parameters);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            LOGGER.error("create instance is failure, cause NoSuchMethodException", e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LOGGER.error("create instance is failure, cause IllegalAccessException", e);
        } catch (InstantiationException e) {
            e.printStackTrace();
            LOGGER.error("create instance is failure, cause InstantiationException", e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            LOGGER.error("create instance is failure, cause InvocationTargetException", e);
        }
        return obj;
    }

    /**
     * 调用方法
     *
     * @param obj
     * @param method
     * @param parameters
     * @return
     */
    public static Object invokeMethod (Object obj, Method method, Object...parameters) {
        Object retResult = null;
        try {
            method.setAccessible(true);
            retResult = method.invoke(obj, parameters);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LOGGER.error("method invoke is failure, cause IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            LOGGER.error("method invoke is failure, cause InvocationTargetException", e);
            e.printStackTrace();
        }
        return retResult;
    }

    /**
     * 设置对象属性
     *
     * @param obj
     * @param field
     * @param value
     */
    public static void setField (Object obj, Field field, Object value) {
        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LOGGER.error("set field failure", e);
            throw new RuntimeException(e);
        }
    }
}

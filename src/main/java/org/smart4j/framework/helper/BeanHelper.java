package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.exception.NotFoundBeanInstanceException;
import org.smart4j.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean助手类
 *
 * @author wangc 2018/6/12
 * @since 1.0.0
 */
public final class BeanHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanHelper.class);

    /**
     * 定义Bean映射（用于存放Bean实力与Class映射关系）
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> cls : beanClassSet) {
            Object instance = ReflectionUtil.createInstance(cls);
            BEAN_MAP.put(cls, instance);
        }
    }

    /**
     * 获取Bean映射
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap () {
        return BEAN_MAP;
    }

    /**
     * 获取Bean实例
     *
     * @param cls
     * @return
     * @throws NotFoundBeanInstanceException
     */
    public static <T> T getBeanInstance (Class<T> cls)
            throws NotFoundBeanInstanceException {
        T instance = null;
        if (BEAN_MAP.containsKey(cls)) {
            instance = (T)BEAN_MAP.get(cls);
        } else {
            throw new NotFoundBeanInstanceException();
        }
        return instance;
    }

}

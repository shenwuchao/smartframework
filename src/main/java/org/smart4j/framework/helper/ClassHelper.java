package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * 类操作助手类
 *
 * @author wangc
 * @since 1.0.0
 */
public final class ClassHelper {
    private static final Set<Class<?>> CLASS_SET;
    static {
        String basePackageName = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackageName);
    }

    /**
     * 获取应用基础包下的所有的类
     * @return
     */
    public static Set<Class<?>> getClassSet () {
        return CLASS_SET;
    }

    /**
     * 获取所有带有service注解的类
     * @return
     */
    public static Set<Class<?>> getServiceClassSet () {
        Set<Class<?>> serviceClassSet = new HashSet<>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Service.class)) {
                serviceClassSet.add(cls);
            }
        }
        return serviceClassSet;
    }

    /**
     * 获取应用包名下的所有带有Controller注解的类
     * @return
     */
    public static Set<Class<?>> getControllerClassSet () {
        Set<Class<?>> controllerClassSet = new HashSet<>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Controller.class)) {
                controllerClassSet.add(cls);
            }
        }
        return controllerClassSet;
    }

    /**
     * 获取应用包名下所有beanl类（包括：Service、Controller）
     * @return
     */
    public static Set<Class<?>> getBeanClassSet () {
        Set<Class<?>> beanClassSet = new HashSet<>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }
}

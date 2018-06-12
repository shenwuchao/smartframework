package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

/**
 * DI助手类
 * @author wangc 2018/6/12
 * @version  1.0.0
 * @since jdk1.8
 */
public class IocHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(IocHelper.class);

    /**
     * 进行service实例依赖注入
     */
    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            Iterator<Map.Entry<Class<?>, Object>> iterator = beanMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Class<?>, Object> entry = iterator.next();
                Class<?> cls = entry.getKey();
                Object instance = entry.getValue();
                Field [] fields = cls.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(fields)) {
                    for (Field field : fields) {
                        if (field.isAnnotationPresent(Inject.class)) {
                            Class<?> fieldClass = field.getType();
                            Object fieldInstance = beanMap.get(fieldClass);
                            if (fieldInstance != null) {
                                ReflectionUtil.setField(instance, field, fieldInstance);
                            }
                        }
                    }
                }
            }
            LOGGER.info("DI success");
        } else {
            LOGGER.warn("Bean count is 0");
        }
    }
}

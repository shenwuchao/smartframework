package org.smart4j.framework;

import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.helper.IocHelper;
import org.smart4j.framework.util.ClassUtil;

/**
 * 加载相应的Helper类
 * @author wangc 2018/6/12
 * @version 1.0.0
 * @since jdk1.8
 */
public final class HelperLoader {
    public static void init () {
        Class<?>[] classList = {ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class};
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(), true);
        }
    }
}

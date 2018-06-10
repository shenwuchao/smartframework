package org.smart4j.framework.annotation;

import java.lang.annotation.*;

/**
 * 服务类注解
 *
 * @author wangc
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
}

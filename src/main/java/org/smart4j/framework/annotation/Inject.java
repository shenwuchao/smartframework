package org.smart4j.framework.annotation;

import java.lang.annotation.*;

/**
 * 依赖注入注解
 *
 * @author wangc
 * @since 1.0.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inject {
}

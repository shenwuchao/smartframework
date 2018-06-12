package org.smart4j.framework.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 集合容器工具类
 * @author wangc
 * @version 1.0.0
 * @since jdk1.8
 *
 */
public final class CollectionUtil {

    /**
     * 判断集合为空
     * @param collection
     * @return
     */
    public static boolean isEmpty (Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断集合不为空
     * @param collection
     * @return
     */
    public static boolean isNotEmpty (Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断map容器为空
     * @param map
     * @return
     */
    public static boolean isEmpty (Map<?, ?> map) {
        return MapUtils.isEmpty(map);
    }

    /**
     * 判断map容器不为空
     * @param map
     * @return
     */
    public static boolean isNotEmpty (Map<?, ?> map) {
        return !isEmpty(map);
    }


}

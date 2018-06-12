package org.smart4j.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数组工具类
 * @author wangc 2018/6/12
 * @version 1.0.0
 * @since jdk1.8
 */
public final class ArrayUtil {
    /**
     * 判断数组对象为空
     * @param array
     * @return
     */
    public static boolean isEmpty (Object [] array) {
        return ArrayUtils.isEmpty(array);
    }

    /**
     * 判断数组对象不为空
     * @param array
     * @return
     */
    public static boolean isNotEmpty (Object [] array) {
        return !isEmpty(array);
    }
}

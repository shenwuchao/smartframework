package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件解析类
 * @author wangc
 * @since 1.0.0
 */
public final class PropsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载属性文件
     *
     * @param fileName
     * @return properties
     * */
    public static Properties loadProps (String fileName) {

        Properties properties = null;
        InputStream is = null;

        try {
            is = Thread.currentThread()
                    .getContextClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new FileNotFoundException(fileName + "file is not found");
            } else {
                properties = new Properties();
                properties.load(is);
                LOGGER.info("load properties success");
            }
        } catch (IOException e) {
            LOGGER.error("load properties file failure", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream failure", e);
                }
            }
        }
        return properties;
    }
    /**
     * 获取字符型属性（默认值为空字符串）
     *
     * @param key
     * @param props
     * @return String
     * */
    public static String getString (Properties props, String key) {
        return getString(props, key, "");
    }
    /**
     * 获取字符型属性（可制定默认值）
     *
     * @param props
     * @param key
     * @param defaultValue
     * @return String
     *
     * */
    public static String getString (Properties props, String key, String defaultValue) {
        String value = defaultValue;
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }
    /**
     * 获取数值型属性（默认值为0）
     *
     * */
    public static int getInt (Properties props, String key) {
        return getInt(props, key, 0);
    }
    /**
     * 获取数值型属性（可制定默认值）
     * */
    public static int getInt (Properties props, String key, int defaultValue) {
        int value = defaultValue;
        if (props.containsKey(key)) {
            value = CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }
    /**
     * 获取布尔类型属性（默认值为false)
     * */
    public static boolean getBoolean (Properties props, String key) {
        return getBoolean(props, key, false);
    }
    /**
     * 获取布尔类型属性（默认值可指定）
     *
     * */
    public static boolean getBoolean (Properties props, String key, boolean defaultValue) {
        boolean value = defaultValue;
        if (props.containsKey(key)) {
            value = CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }
}

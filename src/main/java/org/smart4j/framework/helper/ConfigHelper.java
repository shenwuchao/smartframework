package org.smart4j.framework.helper;

import org.smart4j.framework.ConfigConstant;
import org.smart4j.framework.util.PropsUtil;

import java.util.Properties;

/**
 * 配置文件助手类
 * @author wangc
 * @since 1.0.0
 */
public final class ConfigHelper {
    /**
     * 配置文件常量
     *
     */
    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    /**
     * 获取jdbc驱动
     *
     * @return
     */
    public static String getJdbcDriver () {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
    }
    /**
     * 获取jdbc url
     * @return
     */
    public static String getJdbcUrl () {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
    }

    /**
     * 获取jdbc username
     *
     * @return
     */
    public static String getJdbcUserName () {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USER_NAME);
    }

    /**
     * 获取jdbc password
     * @return
     */
    public static String getJdbcPassword () {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
    }

    /**
     * 获取应用基础包名
     *
     * @return
     */
    public static String getAppBasePackage () {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
    }

    /**
     * 获取应用jsp路径
     * @return
     */
    public static String getAppJspPath () {
        return PropsUtil.getString(CONFIG_PROPS,
                ConfigConstant.APP_JSP_PATH, "/WEB-INF/view/");
    }

    /**
     * 获取静态资源路径
     * @return
     */
    public static String getAppAssetPath () {
        return PropsUtil.getString(CONFIG_PROPS,
                ConfigConstant.APP_ASSET_PATH, "/asset/");
    }

}

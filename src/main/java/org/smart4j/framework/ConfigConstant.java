package org.smart4j.framework;

/**
 * 提供相关配置常量
 *
 * @author wangc
 * @version 1.0.0
 */
public interface ConfigConstant {

    /**
     * 配置文件路径
     */
    String CONFIG_FILE = "smart.properties";
    /**
     * jdbc驱动
     */
    String JDBC_DRIVER = "smart.framework.jdbc.driver";
    String JDBC_URL = "smart.framework.jdbc.url";
    String JDBC_USER_NAME = "smart.framework.jdbc.username";
    String JDBC_PASSWORD = "smart.framework.jdbc.password";

    /**
     * 应用基础信息
     */
    String APP_BASE_PACKAGE = "smart.framework.app.base_package";
    String APP_JSP_PATH = "smart.framework.app.jsp_path";
    String APP_ASSET_PATH = "smart.framework.app.asset_path";
}

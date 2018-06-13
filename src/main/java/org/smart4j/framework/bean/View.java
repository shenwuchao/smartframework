package org.smart4j.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回视图对象
 *
 * @author wangc 2018/6/13
 * @version 1.0.0
 * @since jdk1.8
 *
 */
public class View {
    /**
     * 视图路径
     */
    private String path;
    /**
     * 模型数据
     */
    private Map<String, Object> model;

    public View(String path, Map<String, Object> model) {
        this.path = path;
        this.model = model;
    }

    public View(String path) {
        this.path = path;
        this.model = new HashMap<>();
    }

    /**
     * 向视图对象中增加模型数据
     *
     * @param key
     * @param value
     * @return
     */
    public View addModel (String key, Object value) {
        model.put(key, value);
        return this;
    }

    /**
     * 获取视图路径
     * @return
     */
    public String getPath () {
        return path;
    }

    /**
     * 获取模型数据
     * @return
     */
    public Map<String, Object> getModel () {
        return model;
    }

    /**
     * 根据关键字获取模型数据
     * @param key
     * @return
     */
    public Object getModelValue (String key) {
        if (model.containsKey(key)) {
            return model.get(key);
        }
        return null;
    }
}

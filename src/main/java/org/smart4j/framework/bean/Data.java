package org.smart4j.framework.bean;

/**
 * 返回数据对象
 *
 * @author wangc 2018/6/13
 * @version 1.0.0
 * @since jdk1.8
 */
public class Data {
    /**
     * 模型数据
     */
    private Object model;

    public Data (Object model) {
        this.model = model;
    }
    public Object getModel () {
        return this.model;
    }
}

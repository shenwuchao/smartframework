package org.smart4j.framework.proxy;

/**
 * 代理接口
 *
 * @author wangc
 * @version 1.0.0
 * @since jdk1.8
 */
public interface Proxy {

    /**
     * 执行代理方法
     *
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    Object doProxy (ProxyChain proxyChain) throws Throwable ;
}

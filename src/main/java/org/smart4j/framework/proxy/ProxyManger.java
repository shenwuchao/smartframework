package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 代理管理器
 *
 * @author wangc
 * @version 1.0.0
 * @since jdk1.8
 */
public class ProxyManger {
    /**
     * 创建动态代理对象
     *
     * @param targetClass
     * @param proxyList
     * @return
     */
    public static <T> T createProxy (final Class<T> targetClass, final List<Proxy> proxyList) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(targetClass, o, method, methodProxy, objects, proxyList).doProxyChain();
            }
        });
        return (T)enhancer.create();
    }
}

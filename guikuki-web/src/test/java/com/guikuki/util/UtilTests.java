package com.guikuki.util;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;

/**
 * Utility class for tests.
 * Created by antoniosilvadelpozo on 13/04/14.
 */
public class UtilTests {

    /**
     * Method to Unwrap a bean in case in it is proxied by an aspect.
     * @param bean: bean to be unwrapped
     * @return Unwrapped bean.
     * @throws Exception
     */
    public static Object unwrapProxy(Object bean) throws Exception {
        if (AopUtils.isAopProxy(bean) && bean instanceof Advised) {
            Advised advised = (Advised) bean;
            bean = advised.getTargetSource().getTarget();
        }
        return bean;
    }

}

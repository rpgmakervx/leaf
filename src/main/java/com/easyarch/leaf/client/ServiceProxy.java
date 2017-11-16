package com.easyarch.leaf.client;

import org.easyarch.vsf.init.Init;

import java.util.List;

/**
 * Created by xingtianyu(code4j) on 2017-8-24.
 */
public interface ServiceProxy {

    Object createService(String lookup, Class clazz);

}

package org.easyarch.leaf.client;

import java.util.List;

/**
 * Created by xingtianyu(code4j) on 2017-8-24.
 */
public interface ServiceProxy {

    Object createService(String lookup, Class clazz);

}

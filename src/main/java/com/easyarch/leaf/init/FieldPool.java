package com.easyarch.leaf.init;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xingtianyu(code4j) on 2017-8-20.
 */
public class FieldPool {

    public static Map<Class<?>, Set<String>> excludedFields = new ConcurrentHashMap<Class<?>, Set<String>>();
    public static Map<Class<?>, Set<String>> includedFields = new ConcurrentHashMap<Class<?>, Set<String>>();

}

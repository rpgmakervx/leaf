package com.easyarch.leaf.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xingtianyu(code4j) on 2017-8-23.
 */
public class ServiceCache implements Cache<String,Class> {

    private static Map<String,Class> cacheMap = new ConcurrentHashMap<>();

    @Override
    public void set(String key, Class value) {
        cacheMap.put(key,value);
    }

    @Override
    public Class get(String key) {
        return cacheMap.get(key);
    }

    @Override
    public boolean hit(String key) {
        return cacheMap.containsKey(key);
    }

    @Override
    public Class del(String key) {
        return cacheMap.remove(key);
    }
}

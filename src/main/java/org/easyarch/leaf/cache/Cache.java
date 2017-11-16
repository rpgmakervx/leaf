package org.easyarch.leaf.cache;

/**
 * Created by xingtianyu(code4j) on 2017-8-23.
 */
public interface Cache<K,V> {

    void set(K k, V v);

    V get(K k);

    boolean hit(K k);

    V del(K k);
}

package org.easyarch.leaf.serializer;

/**
 * Created by xingtianyu(code4j) on 2017-8-20.
 */
public interface Serializer<T> {

    public byte[] serialize(T bean);

    public T deserialize(byte[] bytes, Class<T> cls);
}

package org.easyarch.leaf.serializer;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeEnv;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.easyarch.leaf.init.FieldPool;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xingtianyu(code4j) on 2017-8-20.
 */
public class ProtobufSerializer<T> implements Serializer<T> {

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();

    private Schema<T> getSchema(Class<T> cls, Set<String> exclutions) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
        if (schema == null) {
            if (exclutions == null){
                exclutions = new HashSet<>();
            }
            schema = RuntimeSchema.createFrom(cls, exclutions, RuntimeEnv.ID_STRATEGY);
            if (schema != null) {
                cachedSchema.put(cls, schema);
            }
        }
        return schema;
    }

    @Override
    public byte[] serialize(T object) {
        if (object == null){
            return null;
        }
        Class<T> clazz = (Class<T>) object.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        Schema<T> schema = getSchema(clazz, FieldPool.excludedFields.get(clazz));
        byte[] bytes = ProtobufIOUtil.toByteArray(object, schema, buffer);
        return bytes;
    }

    @Override
    public T deserialize(byte[] bytes, Class<T> clazz) {
        if (bytes==null||bytes.length==0){
            return null;
        }
        try {
            T bean = clazz.newInstance();
            Schema<T> schema = getSchema(clazz, FieldPool.excludedFields.get(clazz));
            ProtobufIOUtil.mergeFrom(bytes, bean, schema);
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
//        ProtobufSerializer<RPCEntity> serializer = new ProtobufSerializer();
//        RPCEntity entity = new RPCEntity();
//        entity.setLookUp("DLockServerImpl");
//        entity.setMethodName("lock");
//        byte[] datas = serializer.serialize(entity);
//        RPCEntity obj = serializer.deserialize(datas,RPCEntity.class);
//        System.out.println(obj);
    }
}

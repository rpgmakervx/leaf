package org.easyarch.leaf.init;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by xingtianyu(code4j) on 2017-8-20.
 */
public class ClassPool {
    public static Set<Class> transportClasses = new CopyOnWriteArraySet<>();
}

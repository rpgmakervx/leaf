package com.easyarch.leaf;


import com.easyarch.leaf.context.ClassPathAppContext;

import java.io.File;

/**
 * Created by xingtianyu(code4j) on 2017-8-24.
 */
public class VSF {

    public static AppContext createClassPathAppContext(String packageName){
        return new ClassPathAppContext(packageName);
    }

    public static AppContext createFileAppContext(File file){
        return null;
    }
}

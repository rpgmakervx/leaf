package org.easyarch.leaf.kits;

import java.io.InputStream;

/**
 * Created by xingtianyu(code4j) on 2017-8-21.
 */
public class ResourceKits {
    public static String getClassPath() {
        return ResourceKits.class.getClassLoader().getResource("").getPath();
    }

    public static String getResources(String resource) {
        return ResourceKits.class.getClassLoader().getResource(resource).getPath();
    }

    public static InputStream getResourceAsStream(String resource) {
        return ResourceKits.class.getResourceAsStream(resource);
    }

//    public static String getRootPackage(){
//
//    }

    public static void main(String[] args) {
        System.out.println(getClassPath());
    }
}

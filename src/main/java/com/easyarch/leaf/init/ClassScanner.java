package com.easyarch.leaf.init;


import com.easyarch.leaf.annotation.Implement;
import com.easyarch.leaf.cache.Cache;
import com.easyarch.leaf.cache.ServiceCache;
import com.easyarch.leaf.kits.ResourceKits;
import com.easyarch.leaf.kits.StringKits;
import com.easyarch.leaf.kits.file.FileKits;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xingtianyu(code4j) on 2017-8-20.
 */
public class ClassScanner {

    public static final String CLASSPATH = ResourceKits.getClassPath();

    public static final String CLASS = ".class";
    public static final String POINT = ".";

    private Cache<String,Class> cache = new ServiceCache();

    private static boolean scaned = false;

    private void scan() throws Exception {
        List<File> classFiles = FileKits.listFileRecursive(CLASSPATH);
        int endPoint = CLASSPATH.lastIndexOf(File.separator);

        for (File cf:classFiles){
            String filename = cf.getPath();
            if (!filename.endsWith(CLASS)){
                continue;
            }
            String packagePath = filename.substring(endPoint + 1,filename.length());
            String interfaceName = packagePath.substring(0, filename.lastIndexOf("."))
                    .replace(File.separator,POINT);
            URL[] urls = new URL[]{new URL("file:"+filename)};
            URLClassLoader classLoader = new URLClassLoader(urls);
            Class<?> clazz = classLoader.loadClass(interfaceName);
            addClass(clazz);
        }
    }

    /**
     * 路径扫描，目前不支持通配符*扫描
     * 路径配置文件不存在时则不扫描
     * 系统启动后只加载一次
     * @param packagePath  包名，空字符或单个*符号代表当前工程下所有包
     * @throws Exception
     */
    public void scan(String packagePath) throws Exception {
        if (scaned){
            return;
        }
        scaned = true;
        if (packagePath == null){
            return;
        }

        String copyPath = packagePath.replace(POINT,File.separator);
        String classpath = CLASSPATH + copyPath + "/";
        System.out.println("classpath:"+classpath);
        File file = new File(classpath);
        if (!file.exists()){
            throw new FileNotFoundException("package "+packagePath+" not found");
        }
        if (file.isFile()){
            throw new IllegalArgumentException("please offer a package name");
        }
        //包名第一层作为前缀
        String prefix = packagePath.split("\\"+POINT)[0];
        List<File> classFiles = FileKits.listFileRecursive(classpath);
        for (File cf : classFiles){
            if (!cf.getPath().endsWith(CLASS)){
                continue;
            }
            String filename = cf.getPath();
            String copyFileName = filename.substring(0, filename.lastIndexOf(".")).replace(File.separator,".");
            System.out.println("copyFileName:"+copyFileName);
            String interfaceName = copyFileName.substring(copyFileName.indexOf(prefix),copyFileName.length());
            System.out.println("interfaceName:"+interfaceName);
            URL[] urls = new URL[]{new URL("file:"+filename)};
            URLClassLoader classLoader = new URLClassLoader(urls);
            Class<?> clazz = classLoader.loadClass(interfaceName);
            addClass(clazz);
        }
    }

    private void addClass(Class<?> clazz){
        Implement mapper = clazz.getAnnotation(Implement.class);
        if (mapper != null){
            String lookup = mapper.lookup();
            if (StringKits.isBlank(mapper.lookup())){
                lookup = clazz.getName();
            }
            cache.set(lookup,clazz);
        }
    }

    public static void main(String[] args) throws Exception {
//        ClassScanner scanner = new ClassScanner();
////        scanner.scan("org");
////        System.out.println(scanner.interfaceCache);
//        System.out.println(System.getProperty("user.dir"));
    }
}

package com.easyarch.leaf.context;

import org.easyarch.vsf.init.ClassScanner;

/**
 * Created by xingtianyu(code4j) on 2017-8-24.
 */
public class ClassPathAppContext extends AppContext {

    private String pkgName;

    public ClassPathAppContext(String pkgName){
        this.pkgName = pkgName;
    }

    @Override
    public void loadApi() {
        ClassScanner scanner = new ClassScanner();
        try {
            scanner.scan(pkgName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.easyarch.leaf.init;

import java.util.Set;

/**
 * Created by xingtianyu(code4j) on 2017-8-21.
 */
public class LoaderInit implements Init {

    @Override
    public void init() {
        ClassScanner scan = new ClassScanner();
        try {
            scan.scan("org");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

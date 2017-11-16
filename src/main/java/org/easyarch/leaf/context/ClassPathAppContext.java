package org.easyarch.leaf.context;


import org.apache.logging.log4j.util.Strings;
import org.easyarch.leaf.init.ClassScanner;

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
            if (Strings.isBlank(pkgName)){
                scanner.scan();
                return;
            }
            scanner.scan(pkgName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

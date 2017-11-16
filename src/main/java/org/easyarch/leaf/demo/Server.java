package org.easyarch.leaf.demo;

import org.easyarch.leaf.Leaf;
import org.easyarch.leaf.context.AppContext;

/**
 * @author xingtianyu(code4j) Created on 2017-11-16.
 */
public class Server {

    public static void main(String[] args) {
        AppContext context = Leaf.createClassPathAppContext("org");
        context.start(8081);
    }
}

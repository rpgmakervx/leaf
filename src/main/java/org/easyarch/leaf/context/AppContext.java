package org.easyarch.leaf.context;


import org.easyarch.leaf.server.Processor;

/**
 * Created by xingtianyu(code4j) on 2017-8-24.
 */
abstract public class AppContext {

    private Processor processor;

    public AppContext(){
        this.processor = new Processor();
    }

    public void start(int port){
        loadApi();
        processor.start(port);
    }

    abstract public void loadApi();
}

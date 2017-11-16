package org.easyarch.leaf.kits.file;

import java.io.File;

/**
 * Created by xingtianyu(code4j) on 2017-8-22.
 */
public interface FileFilter {
    public boolean accept(File file);
}

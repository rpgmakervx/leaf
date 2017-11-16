package org.easyarch.leaf.kits;

/**
 * Created by xingtianyu(code4j) on 2017-8-21.
 */
public class StringKits {
    /**
     * 字符串是否是空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(final String str) {
        return str == null || str.length() == 0;
    }
    public static boolean isNotEmpty(final String str) {
        return !isEmpty(str);
    }

    public static boolean isBlank(final String str){
        int strLen;
        if(str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }
}

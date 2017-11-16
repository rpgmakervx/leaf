package org.easyarch.leaf.init;

/**
 * Created by xingtianyu(code4j) on 2017-8-21.
 */
public class ClassItem {

    private String lookUp;

    private Class clazz;

    public String getLookUp() {
        return lookUp;
    }

    public void setLookUp(String lookUp) {
        this.lookUp = lookUp;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassItem classItem = (ClassItem) o;

        return lookUp != null ? lookUp.equals(classItem.lookUp) : classItem.lookUp == null;
    }

    @Override
    public int hashCode() {
        return lookUp != null ? lookUp.hashCode() : 0;
    }
}

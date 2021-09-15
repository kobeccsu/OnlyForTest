package com.leizhou.util;

final public class Dummy {
    public int testing() {
        return var1.length();
    }

    static String var1 = null;

    static String foo() {
        return "foo";
    }

    static String foo(String var2) {
        var1 = var2;
        return "SUCCESS";
    }
}

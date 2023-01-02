package com.example.demo.util;

import java.util.Collection;

public interface Util {

    static boolean isnullOrEmpty(Object valor) {
        return isNull(valor) || isEmpty(valor);
    }

    static boolean isNull(Object valor) {
        return valor == null;
    }

    static boolean isEmpty(Object valor) {
        return valor instanceof Collection ? ((Collection) valor).isEmpty() : "".equals(valor.toString().trim());
    }

}

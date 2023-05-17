package org.dstu.domain;

import java.io.Serializable;

public interface IUniversityPublication extends Serializable {
    default void print() {
        System.out.println(toString());
    }
}

package com.yida.app.InstitutionForThrAged.model;


public interface BaseResult<T> {
    boolean isOk();

    T getData();
}

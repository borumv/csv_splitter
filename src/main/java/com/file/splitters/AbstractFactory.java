package com.file.splitters;
public interface AbstractFactory<T> {

    T create(String splitterType) ;
}

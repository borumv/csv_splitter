package com.file.splitters;
public class SplitterFactory implements AbstractFactory<Splitter>{

    @Override
    public Splitter create(String splitterType) {

        if ("csv".equalsIgnoreCase(splitterType)) {
            System.out.println();
            return new CsvSplitter();
        }

        return null;
    }
}

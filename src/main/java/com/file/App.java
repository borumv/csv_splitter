package com.file;

import com.file.splitters.CsvSplitter;
import com.file.splitters.Splitter;

import java.io.File;
import java.io.IOException;


/**
 * Hello world!
 *
 */
public class App
{
    private static final String SOURCE_SPLIT = "path_to_source";
    private static final String INDEXES_OUTPUT = "path_to_input";

    public static void main( String[] args ) throws IOException {


        Splitter splitter = new CsvSplitter();
        splitter.generateIndexes(new File(SOURCE_SPLIT).toPath(), new File(INDEXES_OUTPUT).toPath(), 1024);
    }
}

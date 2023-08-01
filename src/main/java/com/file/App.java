package com.file;

import com.file.splitters.CsvSplitter;
import com.file.splitters.Splitter;
import com.file.splitters.SplitterFactory;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws IOException {

        String INDEXES_OUTPUT;
        String SOURCE_SPLIT;
        if (args.length < 2) {
            SOURCE_SPLIT = "src/main/resources/targetforsplit.csv";
            INDEXES_OUTPUT = "src/main/resources/result.txt";
        } else {
            SOURCE_SPLIT = args[0];
            INDEXES_OUTPUT = args[1];
        }
        Splitter splitter = new SplitterFactory().create(FilenameUtils.getExtension(SOURCE_SPLIT));
        splitter.generateIndexes(new File(SOURCE_SPLIT).toPath(), new File(INDEXES_OUTPUT).toPath(), 1024);
    }
}

package com.file.splitters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public interface Splitter {
    void split (Path targetToSplit, Path pathSplitFiles, int countParts) throws FileNotFoundException;
    void generateIndexes(Path sourceFile, Path targetFile, int size_buffer) throws IOException;
}

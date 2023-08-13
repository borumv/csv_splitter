package com.file.splitters;

import com.file.dto.IndexTuple;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface Splitter {
    void split (Path targetToSplit, Path pathSplitFiles, int countParts) throws FileNotFoundException;
    void generateIndexes(Path sourceFile, Path targetFile, int size_buffer) throws IOException;

    Map<Integer, IndexTuple> generateIndexMap(Path sourceFile, int size_buffer);
}

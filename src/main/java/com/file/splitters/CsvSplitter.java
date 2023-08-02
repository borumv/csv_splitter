package com.file.splitters;


import com.file.dto.IndexTuple;
import com.file.dto.SplitIndexDto;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CsvSplitter implements Splitter {

    @Override
    public void split(Path targetToSplit, Path pathSplitFiles, int sizeParts) throws FileNotFoundException {
        List<Integer> mapOfIndexSpliting = getSeekIndexes(targetToSplit, sizeParts);
        try (RandomAccessFile src = new RandomAccessFile(targetToSplit.toFile(), "rw")) {
            byte[] buffer = new byte[4024];
            for (Integer integer : mapOfIndexSpliting) {
                RandomAccessFile destination = new RandomAccessFile(pathSplitFiles + "\\split_" + integer + ".txt", "rw");
                src.seek(integer);
                src.read(buffer);
                destination.write(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void generateIndexes(Path sourceFile, Path targetFile, int size_buffer) throws IOException {
        List<Integer> indexesList = getSeekIndexes(sourceFile, 1024);
        ObjectMapper objectMapper = new ObjectMapper();
        FileWriter fileWriter = new FileWriter(targetFile.toFile(), true);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        int indexLeft;
        int indexRight;

        for (int i = 0; i < indexesList.size() - 1; i++) {
            // System.out.println("index №"+ (i + 1) +  " = " + indexesList.get(i) + ", " + indexesList.get(i + 1));
            indexLeft = indexesList.get(i);
            indexRight = indexesList.get(i + 1) - 1;
            SplitIndexDto dto = new SplitIndexDto(indexLeft, indexRight, sourceFile.toRealPath().toString());
            String valueAsJson = objectMapper.writeValueAsString(dto);
            writer.write(valueAsJson);
            writer.write("\n");
            System.out.println(valueAsJson);
        }
        writer.close();

    }

    @Override
    public Map<Integer, IndexTuple> generateIndexMap(Path sourceFile, int size_buffer) {
        List<Integer> indexesList = getSeekIndexes(sourceFile.toAbsolutePath(), size_buffer);
        Map<Integer, IndexTuple> map = new HashMap<>();
        for (int i = 0; i < indexesList.size() - 1; i++) {
            int indexLeft = indexesList.get(i);
            int indexRight = indexesList.get(i + 1) - 1;
            map.put(i, new IndexTuple(indexLeft, indexRight, sourceFile));
        }
        return map;
    }

    public static List<Integer> getSeekIndexes(Path path, int size_buffer){
        List<Integer> seeksArray = new ArrayList<>();
        int certain_index = size_buffer;
        seeksArray.add(0);
        boolean isEnd = false;

        try (RandomAccessFile src = new RandomAccessFile(path.toFile(), "rw")) {
            byte[] test_sick = new byte[1];
            src.seek(certain_index);
            src.read(test_sick);
            while (!isEnd) {
                if (certain_index > src.length()) {
                    certain_index = (int) src.length();
                    src.seek(certain_index);
                    src.read(test_sick);
                    System.out.println("final certain index -  " + certain_index);
                    isEnd = true;
                }
                while (test_sick[0] != (byte) 10 && test_sick[0] != -1) {
                    certain_index++;
                    src.seek(certain_index);
                    src.read(test_sick);
                }
                seeksArray.add(certain_index);
                certain_index += size_buffer;

                src.seek(certain_index);
                src.read(test_sick);
                System.out.println("certain index -  " + certain_index);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return seeksArray;
    }
}

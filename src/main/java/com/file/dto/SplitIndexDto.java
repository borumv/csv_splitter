package com.file.dto;

import lombok.Data;

@Data
public class SplitIndexDto {
    String path;
    int leftIndex;
    int rightIndex;

    public SplitIndexDto(int indexLeft, int indexRight, String path) {
        this.leftIndex = indexLeft;
        this.rightIndex = indexRight;
        this.path = path;
    }
}

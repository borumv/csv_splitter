package com.file.dto;
import java.nio.file.Path;

public record IndexTuple(Integer leftIndex, Integer rightIndex, Path path) {
}

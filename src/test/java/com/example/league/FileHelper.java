package com.example.league;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileHelper {


    public List<String> readFromFile(String file) throws Exception {
        ClassLoader loader = ClassLoader.getSystemClassLoader();

        return Files.readAllLines(Paths.get(loader.getResource(file).toURI()));
    }
}

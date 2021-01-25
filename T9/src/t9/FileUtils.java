package t9;

import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;

public class FileUtils {

    public static List<String> readFileInList(String fileName) {
        Path currentProjectPath = Paths.get("");
        String dictionaryFilePath = currentProjectPath.toAbsolutePath()
                .toString() + "\\src\\t9\\" + fileName;
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(
                    Paths.get(dictionaryFilePath), StandardCharsets.UTF_8
            );
        } catch (IOException exception) {
            System.out.println(exception.getLocalizedMessage());
        }
        return lines;
    }
}

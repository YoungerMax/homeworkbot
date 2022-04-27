package me.youngermax.homeworkbot.api;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import me.youngermax.homeworkbot.Environment;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.*;

@RequiredArgsConstructor
public class HomeworkManager {
    private final Environment environment;
    private final File cacheDirectory;

    public List<Assignment> getAssignments() throws Exception {
        // cache it
        File cacheFile = new File(cacheDirectory, Instant.now().toEpochMilli() + ".csv");
        Path cacheFilePath = cacheFile.toPath();

        try (InputStream stream = environment.getSheetDownloadUrl().openStream()) {
            Files.copy(stream, cacheFilePath, StandardCopyOption.REPLACE_EXISTING);
        }

        // read it
        List<Assignment> assignments = new ArrayList<>();
        String[] header = null;

        try (CSVReader reader = new CSVReader(new FileReader(cacheFile))) {
            Iterator<String[]> iterator = reader.iterator();

            header = iterator.next();

            while (iterator.hasNext()) {
                assignments.add(Assignment.loadFromFields(iterator.next()));
            }
        }

        return assignments;
    }
}

package me.youngermax.homeworkbot;

import lombok.Data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

@Data
public class Environment {
    private final File file;
    private File cacheDirectory;

    private String sheetId;
    private String sheetName;
    private String discordToken;

    private URL sheetDownloadUrl;

    public Environment(File file) {
        this.file = file;
    }

    public void load() {
        try (FileReader reader = new FileReader(file)) {
            Properties properties = new Properties();
            properties.load(reader);

            this.sheetId = properties.getProperty("SHEET_ID");
            this.sheetName = properties.getProperty("SHEET_NAME");
            this.discordToken = properties.getProperty("DISCORD_TOKEN");
            this.cacheDirectory = new File(properties.getProperty("CACHE_DIRECTORY"));

            this.sheetDownloadUrl = new URL(String.format("https://docs.google.com/spreadsheets/d/%s/gviz/tq?tqx=out:csv&sheet=%s", sheetId, sheetName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

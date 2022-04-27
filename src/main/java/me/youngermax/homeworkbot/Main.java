package me.youngermax.homeworkbot;

import me.youngermax.homeworkbot.bot.HomeworkBot;
import me.youngermax.homeworkbot.api.HomeworkManager;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws LoginException, InterruptedException, IOException {
        Environment environment = new Environment(new File("config.properties"));
        environment.load();
        Files.createDirectories(environment.getCacheDirectory().toPath());

        new HomeworkBot(environment, new HomeworkManager(environment, environment.getCacheDirectory())).run();
    }
}

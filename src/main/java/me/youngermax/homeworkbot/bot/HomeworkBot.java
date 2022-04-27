package me.youngermax.homeworkbot.bot;

import lombok.RequiredArgsConstructor;
import me.youngermax.homeworkbot.Environment;
import me.youngermax.homeworkbot.api.HomeworkManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

@RequiredArgsConstructor
public class HomeworkBot {
    private final Environment environment;
    private final HomeworkManager homeworkManager;

    public void run() throws LoginException, InterruptedException {
        JDA jda = JDABuilder.createDefault(environment.getDiscordToken())
                .enableIntents(GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new HomeworkBotMessageHandler(homeworkManager))
                .build();

        jda.awaitReady(); // 2147576832

        registerCommands(jda);
    }

    private void registerCommands(JDA jda) {
        jda.getGuildById(777995016168931358L)
                .updateCommands()
                .addCommands(Commands.slash("homework", "Lists the current homework"))
                .queue();
    }
}

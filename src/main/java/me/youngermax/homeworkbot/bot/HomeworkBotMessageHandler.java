package me.youngermax.homeworkbot.bot;

import lombok.RequiredArgsConstructor;
import me.youngermax.homeworkbot.api.Assignment;
import me.youngermax.homeworkbot.api.HomeworkManager;
import me.youngermax.homeworkbot.util.TimeUtils;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class HomeworkBotMessageHandler extends ListenerAdapter {
    private final HomeworkManager homeworkManager;

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("homework")) {
            try {
                Map<String, List<Assignment>> courseAssignmentMap = new HashMap<>();
                List<Assignment> assignments = homeworkManager.getAssignments();

                for (Assignment assignment : assignments) {
                    if (!courseAssignmentMap.containsKey(assignment.getCourseName())) {
                        courseAssignmentMap.put(assignment.getCourseName(), new ArrayList<>());
                    }

                    courseAssignmentMap.get(assignment.getCourseName()).add(assignment);
                }

                StringBuilder payload = new StringBuilder("Homework as of ").append(TimeUtils.DATE_TIME_FORMAT.format(Instant.now())).append("\n\n");

                courseAssignmentMap.forEach((subject, assignmentList) -> {
                    payload.append("**").append(subject).append("**\n");

                    assignmentList.forEach(assignment -> payload.append("+ ").append(assignment.format()).append("\n"));

                    payload.append("\n");
                });

                event.reply(payload.toString()).queue();
            } catch (Exception e) {
                try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
                    e.printStackTrace(pw);
                    String stackTrace = "Stack trace:\n" + sw;

                    event.reply(":warning: Could not fetch homework assignments!")
                            .addFile(stackTrace.getBytes(StandardCharsets.UTF_8), "DEVELOPER STUFF: Stack trace")
                            .queue();

                    e.printStackTrace();
                } catch (IOException ex) {
                    event.reply(":warning: Could not fetch homework assignments! (could not print stack trace)").queue();

                    ex.printStackTrace();
                }
            }
        }
    }
}

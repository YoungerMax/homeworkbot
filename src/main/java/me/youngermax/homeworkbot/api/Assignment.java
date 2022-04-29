package me.youngermax.homeworkbot.api;

import lombok.Data;
import me.youngermax.homeworkbot.util.TimeUtils;

import java.time.LocalDate;
import java.util.Arrays;

@Data
public class Assignment {
    private String courseName;
    private String teacher;
    private Cluster cluster;
    private Block[] blocks;
    private String title;
    private LocalDate dueDate;
    private String directions;
    private boolean optional;

    public static Assignment loadFromFields(String[] fields) {
        Assignment assignment = new Assignment();

        assignment.courseName = fields[0];
        assignment.teacher = fields[1];
        assignment.cluster = Cluster.valueOf(fields[2]);
        assignment.blocks = Block.getBlocksFromString(fields[3]);
        assignment.title = fields[4];
        assignment.dueDate = parseDate(fields[5]);
        assignment.directions = fields[6];
        assignment.optional = !fields[7].isEmpty();

        return assignment;
    }

    private static LocalDate parseDate(String field) {
        if (!field.contains("/")) return null;

        String[] split = field.split("/");
        return LocalDate.of(Integer.parseInt(split[2]), Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    public String format() {
        StringBuilder builder = new StringBuilder();

        if (title != null) builder.append(title);

        if (optional) builder.append(" (Optional)");

        if (directions != null) builder.append(" | DIRECTIONS: ").append(directions);

        if (dueDate != null) builder.append(" | DUE ").append(TimeUtils.DATE_FORMAT.format(dueDate));

        if (blocks != null) {
            builder.append(" | BLOCKS: ");
            Arrays.stream(blocks).forEach(block -> builder.append(block.getNormalName()).append("/"));
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }
}

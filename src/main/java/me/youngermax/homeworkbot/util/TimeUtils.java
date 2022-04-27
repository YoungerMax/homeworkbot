package me.youngermax.homeworkbot.util;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy @ HH:mm:ss").withZone(ZoneId.systemDefault());
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy").withZone(ZoneId.systemDefault());
}

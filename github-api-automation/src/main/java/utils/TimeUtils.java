package utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    public static String getSafeTimestamp() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        return LocalDateTime.now(ZoneId.systemDefault())
                .format(formatter);
    }


    }


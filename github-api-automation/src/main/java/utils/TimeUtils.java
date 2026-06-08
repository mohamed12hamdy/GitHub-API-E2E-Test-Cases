package utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

        // ISO format (recommended for APIs & logging)
        public static String getCurrentTimestamp() {
            return Instant.now().toString();
        }

        // Human readable format
        public static String getFormattedTimestamp() {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            return LocalDateTime.now(ZoneId.systemDefault())
                    .format(formatter);
        }
    }


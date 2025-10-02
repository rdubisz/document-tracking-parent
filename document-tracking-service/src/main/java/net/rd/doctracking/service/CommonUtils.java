package net.rd.doctracking.service;


import java.time.LocalDateTime;
import java.time.ZoneId;

public class CommonUtils {

    /**
     * The day with an example data
     * 2023-01-01
     */
    public static final LocalDateTime TS_1 = LocalDateTime.of(2023, 2, 1, 0, 0, 0);
    public static final LocalDateTime TS_2 = LocalDateTime.of(2025, 12, 31, 23, 59, 59);

    public static LocalDateTime now() {
        return LocalDateTime.now(ZoneId.of("UTC"));
    }

    public static LocalDateTime paramOrNow(final LocalDateTime param) {
        if(param != null)
            return param;
        else
            return now();
    }
}

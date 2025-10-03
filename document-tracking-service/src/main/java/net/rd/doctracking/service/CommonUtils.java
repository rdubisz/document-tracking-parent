package net.rd.doctracking.service;


import java.time.LocalDateTime;
import java.time.ZoneId;

public class CommonUtils {

    /**
     * Before June 1 - July 1 period
     */
    public static final LocalDateTime TS_1 = LocalDateTime.of(2023, 5, 1, 0, 0, 0);
    /**
     * June 1
     */
    public static final LocalDateTime TS_2 = LocalDateTime.of(2023, 6, 1, 0, 0, 0);
    /**
     * After June 1 - July 1 period
     */
    public static final LocalDateTime TS_3 = LocalDateTime.of(2023, 7, 31, 23, 59, 59);

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

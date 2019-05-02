package utils;

import java.time.LocalDate;

public class DateUtil {
    public static LocalDate createDate(String dateString) {
        LocalDate localDate;
        LocalDate now = LocalDate.now();

        switch(dateString.toLowerCase()) {
            case "today":
                localDate = now;
                break;
            case "yesterday":
                localDate = now.minusDays(1);
                break;
            case "tomorrow":
                localDate = now.plusDays(1);
                break;
            case "last week":
                localDate = now.minusWeeks(1);
                break;
            case "next week":
                localDate = now.plusWeeks(1);
                break;
            default:
                localDate = LocalDate.parse(dateString);
        }
        return localDate;
    }
}

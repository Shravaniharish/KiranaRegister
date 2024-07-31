package api.kirana.register.transactions.helpers;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToLocalDate {
    public static LocalDate convertStringToDate(String dateString) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        return date;
    }
}

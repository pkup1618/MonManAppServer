import java.util.Arrays;
import java.util.GregorianCalendar;

import data.PeriodPlanning;
import data.Transaction;

public class Main {

    public static void main(String[] args) {
        PeriodPlanning period = new PeriodPlanning(
            Arrays.asList(
                new Transaction(
                    "Аванс Андрея",
                    45_000L,
                    new GregorianCalendar(2024, 11, 5).getTime()
                ),
                new Transaction(
                    "Аванс Леры", 
                    30_000L, 
                    new GregorianCalendar(2024, 11, 15).getTime()
                ),
                new Transaction(
                    "Зарплата Андрея", 
                    45_000L, 
                    new GregorianCalendar(2024, 11, 20).getTime()
                ),
                new Transaction(
                    "Зарплаты Леры", 
                    30_000L, 
                    new GregorianCalendar(2024, 11, 30).getTime()
                )
            )
        );

        period.println();
    }
}

package data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    String name;
    Long value;
    Date date;

    public Transaction(String name, Long value, Date date) {
        this.name = name;
        this.value = value;
        this.date = date;
    }

    private String transactionType() {
        if (value < 0) {
            return "Расход";
        }
        if (value > 0) {
            return "Доход";
        }
        return "Ошибочная тразакция";
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
            .append("Транзакция: ").append(name).append("\n")
            .append("Величина транзкции: ").append(value).append("\n")
            .append("Тип транзакции: ").append(transactionType()).append("\n")
            .append("Дата транзакции: ").append(new SimpleDateFormat("dd.MM.yyyy EEEE").format(date)).append("\n");

        return stringBuilder.toString();
    }

    public void println() {
        System.out.println(this);
    }
}
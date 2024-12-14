package data;

import java.util.List;

public class PeriodPlanning {
    List<Transaction> transactions;

    public PeriodPlanning(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        transactions.forEach(transaction -> {
            stringBuilder.append(transaction).append("\n");
        });

        Long sumOfPluses = transactions
                                .stream()
                                .filter(transaction -> transaction.value > 0)
                                .mapToLong(transaction -> transaction.value)
                                .sum();

        Long sumOfMinuses = transactions
                                .stream()
                                .filter(transaction -> transaction.value < 0)
                                .mapToLong(transaction -> transaction.value)
                                .sum();

        stringBuilder.append("Общая сумма доходов: ").append(sumOfPluses).append("\n");
        stringBuilder.append("Общая сумма расходов: ").append(sumOfMinuses).append("\n");

        return stringBuilder.toString();
    }

    public void println() {
        System.out.println(this);
    }
}
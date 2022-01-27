package components.database_handling.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;


/**
 * Класс, представляющий из себя запись о дате из базы данных
 */
@Entity
@Table(name = "date")
public class DateDB {

    public DateDB() { }

    @Id
    @Column(name = "day")
    private Date day;
    public Date getDay() {
        return day;
    }
    public void setDay(Date day) {
        this.day = day;
    }

    @Column(name = "cash_value_on_day_start")
    private double cashValueOnDayStart;
    public double getCashValueOnDayStart() {
        return cashValueOnDayStart;
    }
    public void setCashValueOnDayStart(double cashValueOnDayStart) {
        this.cashValueOnDayStart = cashValueOnDayStart;
    }

    @Column(name = "cash_value_on_day_end")
    private double cashValueOnDayEnd;
    public double getCashValueOnDayEnd() {
        return cashValueOnDayEnd;
    }
    public void setCashValueOnDayEnd(double cashValueOnDayEnd) {
        this.cashValueOnDayEnd = cashValueOnDayEnd;
    }

    @Column(name = "cashless_value_on_day_start")
    private double cashlessValueOnDayStart;
    public double getCashlessValueOnDayStart() {
        return cashlessValueOnDayStart;
    }
    public void setCashlessValueOnDayStart(double cashlessValueOnDayStart) {
        this.cashlessValueOnDayStart = cashlessValueOnDayStart;
    }

    @Column(name = "cashless_value_on_day_end")
    private double cashlessValueOnDayEnd;
    public double getCashlessValueOnDayEnd() {
        return cashlessValueOnDayEnd;
    }
    public void setCashlessValueOnDayEnd(double cashlessValueOnDayEnd) {
        this.cashlessValueOnDayEnd = cashlessValueOnDayEnd;
    }

    /**
     * Сконструировать запись о дне в базу данных
     * @param day день
     * @param cashValueOnDayStart состояние счёта наличными в начале дня
     * @param cashValueOnDayEnd состояние счёта наличными в конце дня
     * @param cashlessValueOnDayStart состояние счёта безналичными в начале дня
     * @param cashlessValueOnDayEnd состояние счёта безналичными в конце дня
     */
    public DateDB(Date day,
                  double cashValueOnDayStart,
                  double cashValueOnDayEnd,
                  double cashlessValueOnDayStart,
                  double cashlessValueOnDayEnd) {

        setDay(day);
        setCashValueOnDayStart(cashValueOnDayStart);
        setCashValueOnDayEnd(cashValueOnDayEnd);
        setCashlessValueOnDayStart(cashlessValueOnDayStart);
        setCashlessValueOnDayEnd(cashlessValueOnDayEnd);
    }
}

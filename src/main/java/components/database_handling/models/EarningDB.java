package components.database_handling.models;

import javax.persistence.*;
import java.sql.Date;

/**
 * Класс, представляющий из себя запись о доходе из базы данных
 */
@Entity
@Table(name = "earning")
public class EarningDB {

    public EarningDB() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    @Column(name = "earning_name")
    private String earningName;
    public String getEarningName() {
        return earningName;
    }
    public void setEarningName(String earningName) {
        this.earningName = earningName;
    }

    @Column(name = "earning_type")
    private String earningType;
    public String getEarningType() {
        return earningType;
    }
    public void setEarningType(String earningType) {
        this.earningType = earningType;
    }

    @Column(name = "earning_cost")
    private Double earningCost;
    public Double getEarningCost() {
        return earningCost;
    }
    public void setEarningCost(Double earningCost) {
        this.earningCost = earningCost;
    }

    @Column(name = "count")
    private Long count;
    public Long getCount() {
        return count;
    }
    public void setCount(Long count) {
        this.count = count;
    }

    @Column(name = "day")
    private Date day;
    public Date getDay() {
        return day;
    }
    public void setDay(Date day) {
        this.day = day;
    }

    @Column(name = "payment_type")
    private String paymentType;
    public String getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }


    /**
     * Сконструировать запись о доходе в базу данных
     * @param earningName название
     * @param earningType тип
     * @param earningCost цена (за штуку)
     * @param count количество
     * @param day день
     * @param paymentType способ оплаты (cash/card)
     */
    public EarningDB(String earningName,
                     String earningType,
                     Double earningCost,
                     Long count,
                     Date day,
                     String paymentType) {

        setEarningName(earningName);
        setEarningType(earningType);
        setEarningCost(earningCost);
        setCount(count);
        setDay(day);
        setPaymentType(paymentType);
    }
}

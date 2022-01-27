package components.database_handling.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "purchase")
public class PurchaseDB {

    public PurchaseDB() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    @Column(name = "purchase_name")
    private String purchaseName;
    public String getPurchaseName() {
        return purchaseName;
    }
    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    @Column(name = "purchase_type")
    private String purchaseType;
    public String getPurchaseType() {
        return purchaseType;
    }
    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    @Column(name = "purchase_cost")
    private Double purchaseCost;
    public Double getPurchaseCost() { return purchaseCost; }
    public void setPurchaseCost(Double purchaseCost) { this.purchaseCost = purchaseCost; }

    @Column(name = "count")
    private Long count;
    public long getCount() {
        return count;
    }
    public void setCount(long count) {
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
    public String getPaymentType() { return paymentType; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }


    /**
     * Сконструировать запись о расходе в базу данных
     * @param purchaseName название
     * @param purchaseType тип
     * @param purchaseCost цена (за штуку)
     * @param count количество
     * @param day день
     * @param paymentType способ оплаты (cash/card)
     */
    public PurchaseDB(String purchaseName,
                      String purchaseType,
                      double purchaseCost,
                      long count,
                      Date day,
                      String paymentType) {

        setPurchaseName(purchaseName);
        setPurchaseType(purchaseType);
        setPurchaseCost(purchaseCost);
        setCount(count);
        setDay(day);
        setPaymentType(paymentType);
    }
}
//todo DONE
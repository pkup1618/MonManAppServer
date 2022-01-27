package components.database_handling;

import components.database_handling.models.DateDB;
import components.database_handling.models.EarningDB;
import components.database_handling.models.PurchaseDB;

import java.sql.Date;
import java.util.List;

public interface DBHandler {

    List<DateDB> getAllDays();
    List<PurchaseDB> getAllPurchases();
    List<EarningDB> getAllEarnings();

    void setDay(DateDB date);
    void setPurchase(PurchaseDB purchase);
    void setEarning(EarningDB earning);

    List<PurchaseDB> getPurchasesInTimePeriod(Date firstDay, Date lastDay);
    List<EarningDB> getEarningsInTimePeriod(Date firstDay, Date lastDay);

    void deleteDay(Date date);
    void deletePurchase(Long id);
    void deleteEarning(Long id);
}

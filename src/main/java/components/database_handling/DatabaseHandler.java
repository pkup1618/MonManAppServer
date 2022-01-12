package components.database_handling;


import components.database_handling.models.DateDB;
import components.database_handling.models.EarningDB;
import components.database_handling.models.PurchaseDB;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Класс для взаимодействия с базой данных через JDBC Driver
 */
public class DatabaseHandler {

    private static DatabaseHandler uniqueInstance;

    private DatabaseHandler() {}

    public static DatabaseHandler getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new DatabaseHandler();
        return uniqueInstance;
    }

    private static Connection connection;


    // Понимаю, что указание паролей в коде - это очень плохая практика,
    // но всё сделано для локального пользования
    static {
        Driver postgreSqlDriver;
        connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            postgreSqlDriver = DriverManager.getDriver("jdbc:postgresql://localhost:5709/money_management_db");
            DriverManager.registerDriver(postgreSqlDriver);

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5709/money_management_db",
                    "postgres", "password");

            if (connection == null) {
                System.out.println("Нет соединения с БД!");
                System.exit(0);
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Получить все записи о днях из базы данных
     * @return записи из базы данных
     */
    public List<DateDB> getAllDays() {

        List<DateDB> days = new ArrayList<DateDB>();
        ResultSet rs = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM date");
            rs = statement.executeQuery();

            while(rs.next()) {
                DateDB day = new DateDB(
                        rs.getDate(1),
                        rs.getDouble(2),
                        rs.getDouble(3),
                        rs.getDouble(4),
                        rs.getDouble(5));
                days.add(day);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return days;
    }


    /**
     * Получить все записи о расходах из базы данных
     * @return записи из базы данных
     */
    public List<PurchaseDB> getAllPurchases() {

        List<PurchaseDB> purchases = new ArrayList<PurchaseDB>();
        ResultSet rs = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM purchase");
            rs = statement.executeQuery();

            while(rs.next()) {
                PurchaseDB purchase = new PurchaseDB(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getLong(4),
                        rs.getDate(5),
                        rs.getString(6));
                purchases.add(purchase);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return purchases;
    }


    /**
     * Получить все записи о доходах из базы данных
     * @return записи из базы данных
     */
    public List<EarningDB> getAllEarnings() {

        List<EarningDB> earnings = new ArrayList<EarningDB>();
        ResultSet rs = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM earning");
            rs = statement.executeQuery();

            while(rs.next()) {
                EarningDB earning = new EarningDB(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getLong(4),
                        rs.getDate(5),
                        rs.getString(6));
                earnings.add(earning);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return earnings;
    }


    /**
     * Добавить запись о дне в базу данных
     * @param dateDB объект дня
     */
    public void setDay(DateDB dateDB) {
        try {
            PreparedStatement statement = connection.
                    prepareStatement("INSERT INTO date VALUES(?, ?, ?, ?, ?)");

            statement.setDate(1, dateDB.getDay());
            statement.setDouble(2, dateDB.getCashValueOnDayStart());
            statement.setDouble(3, dateDB.getCashValueOnDayEnd());
            statement.setDouble(4, dateDB.getCashlessValueOnDayStart());
            statement.setDouble(5, dateDB.getCashlessValueOnDayEnd());

            statement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Добавить запись о расходе в базу данных
     * @param purchaseDB объект расхода
     */
    public void setPurchase(PurchaseDB purchaseDB) {
        try {
            PreparedStatement statement = connection.
                    prepareStatement("INSERT INTO purchase VALUES(?, ?, ?, ?, ?, ?)");

            statement.setString(1, purchaseDB.getPurchaseName());
            statement.setString(2, purchaseDB.getPurchaseType());
            statement.setDouble(3, purchaseDB.getPurchaseCost());
            statement.setLong(4, purchaseDB.getCount());
            statement.setDate(5, purchaseDB.getDay());
            statement.setString(6, purchaseDB.getPaymentType());

            statement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Добавить запись о доходе в базу данных
     * @param earningDB объект дохода
     */
    public void setEarning(EarningDB earningDB) {
        try {
            PreparedStatement statement = connection.
                    prepareStatement("INSERT INTO earning VALUES(?, ?, ?, ?, ?, ?)");

            statement.setString(1, earningDB.getEarningName());
            statement.setString(2, earningDB.getEarningType());
            statement.setDouble(3, earningDB.getEarningCost());
            statement.setLong(4, earningDB.getCount());
            statement.setDate(5, earningDB.getDay());
            statement.setString(6, earningDB.getPaymentType());

            statement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

    }


/*
    public boolean checkDateForExistence(Date date) {
        Boolean result = null;
        try {
            PreparedStatement statement = connection.
                    prepareStatement("SELECT * FROM date WHERE day = ?");
            statement.setDate(1, date);
            ResultSet resultSet = statement.executeQuery();

            resultSet.last();
            int size = resultSet.getRow();
            resultSet.beforeFirst();
            result = (size == 1);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
*/


    /**
     * Получить все записи о расходах за временной промежуток
     * от дня до дня (включительно) из базы данных
     * @param firstDay объект дня (от которого отсчёт)
     * @param lastDay объект дня (докоторого отсчёт)
     * @return записи из базы данных
     */
    public List<PurchaseDB> getPurchasesInTimePeriod(Date firstDay, Date lastDay) {

        ResultSet rs = null;
        List<PurchaseDB> purchases = new ArrayList<PurchaseDB>();

        try {
            PreparedStatement statement = connection.
                    prepareStatement("SELECT * FROM purchase WHERE day BETWEEN ? AND ?");
            statement.setDate(1, firstDay);
            statement.setDate(2, lastDay);

            rs = statement.executeQuery();

            while(rs.next()) {
                PurchaseDB purchase = new PurchaseDB(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getLong(4),
                        rs.getDate(5),
                        rs.getString(6));
                purchases.add(purchase);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return purchases;
    }


    /**
     * Получить все записи о доходах за временной промежуток
     * от дня до дня (включительно) из базы данных
     * @param firstDay объект дня (от которого отсчёт)
     * @param lastDay объект дня (докоторого отсчёт)
     * @return записи из базы данных
     */
    public List<EarningDB> getEarningsInTimePeriod(Date firstDay, Date lastDay) {
        ResultSet rs = null;
        List<EarningDB> earnings = new ArrayList<EarningDB>();

        try {
            PreparedStatement statement = connection.
                    prepareStatement("SELECT * FROM earning WHERE day BETWEEN ? AND ?");
            statement.setDate(1, firstDay);
            statement.setDate(2, lastDay);

            rs = statement.executeQuery();

            while(rs.next()) {
                EarningDB earning = new EarningDB(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getLong(4),
                        rs.getDate(5),
                        rs.getString(6));
                earnings.add(earning);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return earnings;
    }


    public void deleteDay(DateDB date) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM date WHERE day = (?)");
            statement.setDate(1, date.getDay());

            statement.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Удалить запись с определённым идентификатором из таблицы earning
     * @param earning доход
     */
    public void deleteEarning(EarningDB earning) {
        try {
            PreparedStatement statement = connection.
                    prepareStatement("DELETE FROM earning WHERE id = (?)");
            statement.setLong(1, earning.getId());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Удалить запись с определённым идентификатором из таблицы purchase
     * @param purchase расход
     */
    public void deletePurchase(PurchaseDB purchase) {
        try {
            PreparedStatement statement = connection.
                    prepareStatement("DELETE FROM purchase WHERE id = (?)");
            statement.setLong(1, purchase.getId());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Преобразовать записи из формата ResultSet в список JSON
     * @param resultSet записи из базы данных
     * @return записи из базы данных в виде списка JSON
     */
    public JSONArray serializeListToJson(ResultSet resultSet) {

        JSONObject json = new JSONObject();

        JSONArray allJsonRows = new JSONArray();
        try {
            int numberColumns = resultSet.getMetaData().getColumnCount();
            ResultSetMetaData metaData = resultSet.getMetaData();

            while(resultSet.next()) {
                JSONObject jsonRow = new JSONObject();

                for(int i = 1; i <= numberColumns; i++) {
                    String ColumnName = metaData.getColumnName(i);
                    String RowElem = resultSet.getString(i);
                    jsonRow.put(ColumnName, RowElem);
                }
                allJsonRows.add(jsonRow);
            }
            resultSet.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return allJsonRows;
    }
}
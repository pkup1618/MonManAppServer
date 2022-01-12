package servlets.send_command_servlets.set_servlets;

import components.database_handling.models.PurchaseDB;
import org.json.simple.JSONObject;
import servlets.send_command_servlets.OperationWithElemServlet;

import java.sql.Date;


public class SetPurchaseServlet extends OperationWithElemServlet {

    @Override
    protected void makeResponseToDatabase() {

        String purchaseName = (String) jsonRequestBody.get("purchase_name");
        String purchaseType = (String) jsonRequestBody.get("purchase_type");
        Double purchaseCost = (Double) jsonRequestBody.get("purchase_cost");
        Long count = (Long) jsonRequestBody.get("count");
        Date dateForModel = convertDateFromStringDate( (String) jsonRequestBody.get("day"));
        String paymentType = (String) jsonRequestBody.get("payment_type");


        databaseHandler.setPurchase(new PurchaseDB(purchaseName,
                purchaseType, purchaseCost, count, dateForModel, paymentType));
    }


    @Override
    protected void setRequestJsonTemplate() {

        requestJsonTemplate = new JSONObject();
        requestJsonTemplate.put("purchase_name", "null");
        requestJsonTemplate.put("purchase_type", "null");
        requestJsonTemplate.put("purchase_cost", 0);
        requestJsonTemplate.put("count", 0);
        requestJsonTemplate.put("day", "00/00/0000");
        requestJsonTemplate.put("payment_type", "null");
    }
}
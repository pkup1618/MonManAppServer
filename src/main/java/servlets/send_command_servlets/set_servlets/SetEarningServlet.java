package servlets.send_command_servlets.set_servlets;

import components.database_handling.models.EarningDB;
import org.json.simple.JSONObject;
import servlets.send_command_servlets.OperationWithElemServlet;

import java.sql.Date;


public class SetEarningServlet extends OperationWithElemServlet {

    @Override
    protected void makeResponseToDatabase() {

        String earningName = (String) jsonRequestBody.get("earning_name");
        String earningType = (String) jsonRequestBody.get("earning_type");
        Double earningCost = (Double) jsonRequestBody.get("earning_cost");
        Long count = (Long) jsonRequestBody.get("count");
        Date dateForModel = convertDateFromStringDate( (String) jsonRequestBody.get("day"));
        String paymentType = (String) jsonRequestBody.get("payment_type");


        databaseHandler.setEarning(new EarningDB(earningName,
                earningType, earningCost, count, dateForModel, paymentType));
    }


    @Override
    protected void setRequestJsonTemplate() {
        requestJsonTemplate = new JSONObject();

        requestJsonTemplate.put("earning_name", "");
        requestJsonTemplate.put("earning_type", "");
        requestJsonTemplate.put("earning_cost", 0);
        requestJsonTemplate.put("count", 0);
        requestJsonTemplate.put("day", "");
        requestJsonTemplate.put("payment_type", "");
    }
}
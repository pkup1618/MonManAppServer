package servlets.send_command_servlets.set_servlets;

import components.database_handling.models.DateDB;
import org.json.simple.JSONObject;
import servlets.send_command_servlets.OperationWithElemServlet;

import java.sql.Date;


public class SetDayServlet extends OperationWithElemServlet {

    @Override
    protected void makeResponseToDatabase() {

        String stringDate = (String) jsonRequestBody.get("day");
        Date date = convertDateFromStringDate(stringDate);
        double cashValueOnDayStart = (Double) jsonRequestBody.get("cash_value_on_day_start");
        double cashValueOnDayEnd = (Double) jsonRequestBody.get("cash_value_on_day_end");
        double cashlessValueOnDayStart = (Double) jsonRequestBody.get("cashless_value_on_day_start");
        double cashlessValueOnDayEnd = (Double) jsonRequestBody.get("cashless_value_on_day_end");


        databaseHandler.setDay(new DateDB(date, cashValueOnDayStart,
                cashValueOnDayEnd, cashlessValueOnDayStart, cashlessValueOnDayEnd));
    }

    protected void setRequestJsonTemplate() {
        requestJsonTemplate = new JSONObject();

        requestJsonTemplate.put("day", "");
        requestJsonTemplate.put("cash_value_on_day_start", 0);
        requestJsonTemplate.put("cash_value_on_day_end", 0);
        requestJsonTemplate.put("cashless_value_on_day_start", 0);
        requestJsonTemplate.put("cashless_value_on_day_end", 0);
    }
}
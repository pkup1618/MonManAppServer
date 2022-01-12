package servlets.get_command_servlets.time_period_servlets;

import components.support_classes.exceptions.IncorrectBodyFormatException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import servlets.ParentServlet;

import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;


/**
 * Фундамент сервлетов для получения записей какого-либо типа (дохода, расхода) из базы данных
 * за временной период от одного дня до другого (включительно)
 */
public abstract class GetElemsInTimePeriodServlet extends ParentServlet {

    protected List data;


    protected abstract void makeResponseToDatabase(Date lessDate, Date moreDate);


    protected void setRequestJsonTemplate() {
        requestJsonTemplate = new JSONObject();
        requestJsonTemplate.put("preceding_date", "00/00/0000");
        requestJsonTemplate.put("following_date", "00/00/0000");

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        PrintWriter responseWriter = getResponsePrintWriter(response);
        String requestBody = getRequestBody(request);
        setRequestJsonTemplate();

        try{
            validateRequest(request, requestBody, requestJsonTemplate);
            JSONObject jsonRequestBody = parseJsonFromString(requestBody);

            Date precedingDate = convertDateFromStringDate( (String) jsonRequestBody.get("preceding_date"));
            Date followingDate = convertDateFromStringDate( (String) jsonRequestBody.get("following_date"));

            makeResponseToDatabase(precedingDate, followingDate);

            String jsonAnswer = jsonSerializer.serializeObject(data);
            responseWriter.println(jsonAnswer);

        }
        catch(IncorrectBodyFormatException e) {

            JSONObject jsonAnswer = new JSONObject();
            jsonAnswer.put("status", false);
            responseWriter.println(jsonAnswer);
        }

    }
}

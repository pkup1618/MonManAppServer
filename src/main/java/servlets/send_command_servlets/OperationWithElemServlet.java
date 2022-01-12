package servlets.send_command_servlets;


import components.support_classes.exceptions.IncorrectBodyFormatException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import servlets.ParentServlet;


/**
 * Фундамент сервлетов для операции с записями какого-либо типа (дня, дохода, расхода) в базу данных
 */
public abstract class OperationWithElemServlet extends ParentServlet {

    protected JSONObject jsonRequestBody;

    protected abstract void makeResponseToDatabase();


    /**
     * Выполнить post-запрос
     * @param request пришедший запрос
     * @param response ответ
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String requestBody = getRequestBody(request);
        JSONObject jsonAnswer = new JSONObject();

        try {
            setRequestJsonTemplate();
            validateRequest(request, requestBody, requestJsonTemplate);

            jsonRequestBody = parseJsonFromString(requestBody);

            makeResponseToDatabase();
            jsonAnswer.put("status" , true);
            getResponsePrintWriter(response).println(jsonAnswer);
        }
        catch (IncorrectBodyFormatException e) {

            jsonAnswer.put("status", false);
            getResponsePrintWriter(response).println(jsonAnswer);
        }
    }
}

package servlets.get_command_servlets.all_data_servlets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import servlets.ParentServlet;

import java.io.PrintWriter;

import java.sql.ResultSet;
import java.util.List;


/**
 * Фундамент сервлетов для получения записей какого-либо типа (дня, дохода, расхода) из базы данных за всё время
 */
public abstract class GetAllElemsServlet extends ParentServlet {

    protected List data;


    protected abstract void makeResponseToDatabase();


    @Override
    protected void setRequestJsonTemplate() { }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        makeResponseToDatabase();
        String jsonAnswer = jsonSerializer.serializeObject(data);

        PrintWriter responseWriter = getResponsePrintWriter(response);
        responseWriter.println(jsonAnswer);
    }
}

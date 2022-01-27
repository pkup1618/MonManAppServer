package servlets.send_command_servlets.delete_servlets;

import org.json.simple.JSONObject;
import servlets.send_command_servlets.OperationWithElemServlet;

public class DeletePurchaseServlet extends OperationWithElemServlet{

    @Override
    protected void makeResponseToDatabase() {

        Long id = (Long) jsonRequestBody.get("id");
        databaseHandler.deletePurchase(id);
    }


    @Override
    protected void setRequestJsonTemplate() {

        requestJsonTemplate = new JSONObject();

        requestJsonTemplate.put("id", 0);
    }
}

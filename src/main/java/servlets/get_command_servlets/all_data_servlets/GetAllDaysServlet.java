package servlets.get_command_servlets.all_data_servlets;


public class GetAllDaysServlet extends GetAllElemsServlet {

    @Override
    protected void makeResponseToDatabase() {
        data = databaseHandler.getAllDays();
    }
}

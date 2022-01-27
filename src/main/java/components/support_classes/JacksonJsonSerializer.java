package components.support_classes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import components.database_handling.JpaDBHandler;

import javax.persistence.Persistence;

public class JacksonJsonSerializer implements JsonSerializer {


    private static JacksonJsonSerializer uniqueInstance;
    private JacksonJsonSerializer() {}
    public static JacksonJsonSerializer getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new JacksonJsonSerializer();
        return uniqueInstance;
    }


    @Override
    public String serializeObject(Object o) {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(o);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}

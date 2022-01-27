package components.support_classes;

import components.support_classes.exceptions.IncorrectBodyFormatException;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


/**
 * Класс с фунционалом для обработки JSON-объектов
 */
public class JsonHandler {


    /**
     * Преобразовать JSON-строку в JSON-объект
     * @param stringJson строка ( например { "field": "value" } )
     * @return JSON-объект
     */
    public static JSONObject parseJsonFromString(String stringJson) {

        JSONObject requestJsonObject = null;
        try {
            requestJsonObject = (JSONObject) JSONValue.parseWithException(stringJson);
        }
        catch(org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return requestJsonObject;
    }


    /**
     * Проврить JSON-строку на возможность преобразования в JSON-объект
     * @param stringJson JSON-строка
     * @throws IncorrectBodyFormatException если не прошёл проверку
     */
    public static void checkRequestBodyParseAbility(String stringJson) throws IncorrectBodyFormatException {

        try {
            JSONValue.parseWithException(stringJson);
        }
        catch(org.json.simple.parser.ParseException e) {
            throw new IncorrectBodyFormatException();
        }
    }
}
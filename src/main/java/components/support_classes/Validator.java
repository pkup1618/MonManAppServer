package components.support_classes;

import components.support_classes.exceptions.IncorrectBodyFormatException;

import jakarta.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;


/**
 * Класс, который проверяет корректность http-запроса, содержащего JSON
 * Можно представить его как конвейер, на котором разбирается запрос, и в случае проблемы сигнализируется ошибка
 */
public class Validator {

    /**
     * Проверить запрос
     * @param request запрос
     * @param requestBody тело запроса
     * @param template шаблон для проверки на соответствие
     * @throws IncorrectBodyFormatException если не прошёл проверку
     */
    public static void validateRequest(HttpServletRequest request, String requestBody, JSONObject template) throws IncorrectBodyFormatException {

        JSONObject jsonRequestBody;
        JsonHandler.checkRequestBodyParseAbility(requestBody);
        jsonRequestBody = JsonHandler.parseJsonFromString(requestBody);

        checkContentType(request);
        checkJsonSize(jsonRequestBody, template);
        checkJsonFieldNames(jsonRequestBody, template);
    }


    /**
     * Проверить поля JSON-объекта
     * @param jsonObject JSON-объект
     * @param template JSON-объект (шаблон, для соответствия)
     * @throws IncorrectBodyFormatException если не прошёл проверку
     */
    private static void checkJsonFieldNames(JSONObject jsonObject, JSONObject template) throws IncorrectBodyFormatException {

        for (Object key : template.keySet()) {
            if (!jsonObject.containsKey(key)) {
                throw new IncorrectBodyFormatException();
            }
        }
    }


    /**
     * Проверить количество полей JSON-объекта
     * @param jsonObject JSON-объект
     * @param template JSON-объект (шаблон, для соответствия)
     * @throws IncorrectBodyFormatException если не прошёл проверку
     */
    private static void checkJsonSize(JSONObject jsonObject, JSONObject template) throws IncorrectBodyFormatException {
        if (!(jsonObject.size() == template.size())) {
            throw new IncorrectBodyFormatException();
        }
    }


    /**
     * Проверить заголовок Content-Type на соответствие значению application/json
     * @param request запрос
     * @throws IncorrectBodyFormatException если не прошёл проверку
     */
    private static void checkContentType(HttpServletRequest request) throws IncorrectBodyFormatException {
        String contentType = request.getHeader("Content-Type");
        if (!(contentType.compareTo("application/json") == 0)) {
            throw new IncorrectBodyFormatException();
        }
    }
}
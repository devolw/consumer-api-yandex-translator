import com.fasterxml.jackson.core.JsonProcessingException;
import model.YandexResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Translator {
    public static void main(String[] args) throws JsonProcessingException {

        // Читаем ввод пользователя (на русском языке)
        System.out.println("Введите предложение на русском языке : ");
        Scanner scanner = new Scanner(System.in);
        String stringToTranslate = scanner.nextLine();

        // Создаем restTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Адрес на который будет отправлен запрос
        String url = "https://translate.api.cloud.yandex.net/translate/v2/translate";
        // Токен пользователя
        String token = "your_token";

        // Хэдеры требуемые API переводчика
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);

        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("folderId", "b1gh5nqtu89kanaaqf3c");
        jsonData.put("targetLanguageCode", "en");
        jsonData.put("texts", "[" + stringToTranslate + "]");

        // Запрос
        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonData, headers);

        // Ответ
        YandexResponse response = restTemplate.postForObject(url, request, YandexResponse.class);
        System.out.println("Перевод : " + response.getTranslations().get(0).getText());
    }
}
package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CustomResponses;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Getter;

import java.util.Map;

public class ApiRunner {
    //create method HTTP Get

    @Getter
    private static CustomResponses customResponses;

    public static void runGet(String path){
        // url domain and path
        // parametr and no parameters we will use overloading
        // token

        String url = Config.getProperty("cashWiseUrl") + path ;
        String token = CashWiseAutorizationToken.getToken();

        Response response = RestAssured.given().auth().oauth2(token).get(url);

        System.out.println("status code : " + response.statusCode());

        ObjectMapper mapper = new ObjectMapper();

        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
        }
        catch (JsonProcessingException e) {
            System.out.println("This is most likely List of responses ");

        }


    }
    public static void runGet(String path, Map<String,Object> params){

        String url = Config.getProperty("cashWiseUrl") + path ;
        String token = CashWiseAutorizationToken.getToken();

        Response response = RestAssured.given().auth().oauth2(token).get(url);

        System.out.println("status code : " + response.statusCode());

        ObjectMapper mapper = new ObjectMapper();

        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
            customResponses.setResponseBody(response.asString());
        }
        catch (JsonProcessingException e) {
            System.out.println("This is most likely List of responses ");

        }
    }

    }

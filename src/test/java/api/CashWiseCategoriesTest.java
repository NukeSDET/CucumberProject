package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import entities.CustomResponses;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.ApiRunner;
import utilities.CashWiseAutorizationToken;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class CashWiseCategoriesTest {
    @Test
    public void createCategory() throws JsonProcessingException {
        String token = CashWiseAutorizationToken.getToken();
        String url = Config.getProperty("cashWiseUrl") + "/api/myaccount/categories";
        RequestBody requestBody = new RequestBody();
        requestBody.setCategory_title("teaching");
        requestBody.setCategory_description("income from teaching");
        requestBody.setFlag(true);

        // above we have ready request body that we build now we can hit API

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);

        System.out.println("status code : " + response.statusCode());
        response.prettyPrint();

        String jsonResponse = response.asString();

        ObjectMapper mapper = new ObjectMapper();
        CustomResponses customResponses = mapper.readValue(jsonResponse, CustomResponses.class);
        // lines 32 we are storing our response as a String , then we are using mapper
        // which comes from jackson library , and we are mapping that we stored as String to our POJO customResponse Class
        //Now , we can get any varribale in customResponse class using geters and settters .
        //Geeters and seeters generate behind the sscene with the help of lambok plugin and lambok dependency

        System.out.println("category-ID : " + customResponses.getCategory_ID());
        System.out.println("created : " + customResponses.getCreated());

    }

}


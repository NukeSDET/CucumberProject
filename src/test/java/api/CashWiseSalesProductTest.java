package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.CashWiseAutorizationToken;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CashWiseSalesProductTest {
    // we need token
    @Test
            public void verifyProductList(){


    String token = CashWiseAutorizationToken.getToken();
    // we need endpoints

    String url = Config.getProperty("cashWiseUrl") + "/api/myaccount/products";
    //we need parameters
     Map <String, Object> params = new HashMap<>();
     
     params.put("page", 1);
     params.put("size", 4) ;

        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
        System.out.println("status code: " + response.statusCode());
        response.prettyPrint();



}}

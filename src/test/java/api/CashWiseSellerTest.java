package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import entities.CustomResponses;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.ApiRunner;
import utilities.CashWiseAutorizationToken;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class CashWiseSellerTest {

    @Test
    public void getSingleSeller (){
        int id =1772 ;
        String token = CashWiseAutorizationToken.getToken();
        String url = Config.getProperty("cashWiseUrl")+"/api/myaccount/sellers/" + id;

        Response response= RestAssured.given().auth().oauth2(token).get(url);
        System.out.println("status code : " + response.statusCode());
        response.prettyPrint();
    }

    @Test
    public void getAlLSellers (){
        String token = CashWiseAutorizationToken.getToken();
       String url = Config.getProperty("cashWiseUrl")+"/api/myaccount/sellers";

       Map<String,Object> params = new HashMap<>();
       params.put("isArchived",false);
       params.put("page",1);
       params.put("size",4);

       Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
        System.out.println("status code " + response.statusCode());
        response.prettyPrint();



    }

    @Test
    public void createSeller () throws JsonProcessingException {
        String token = CashWiseAutorizationToken.getToken();
        String url = Config.getProperty("cashWiseUrl")+ "/api/myaccount/sellers/" ;

        RequestBody requestBody = new RequestBody() ;

        requestBody.setCompany_name("Netflix");
        requestBody.setSeller_name("Mike");
        requestBody.setEmail("mike567687678678902@gmail.com");
        requestBody.setPhone_number("19179374363");
        requestBody.setAddress("Washington , Co");

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);

        System.out.println("status code : " + response.statusCode());
        response.prettyPrint();

        // we hit createSeller API , which returns us created seller with seller ID , no we want to hit
        // get seller API with that id that we receive from response

        ObjectMapper mapper = new ObjectMapper();
        CustomResponses customResponses = mapper.readValue(response.asString(), CustomResponses.class);
        System.out.println("seller id " + customResponses.getSeller_id());

        Assert.assertEquals(200,response.statusCode());


    }
    @Test
    public void createManySeller () {
        String token = CashWiseAutorizationToken.getToken();
        String url = Config.getProperty("cashWiseUrl") + "/api/myaccount/sellers";
        Faker faker = new Faker();
        RequestBody requestBody = new RequestBody();


        for (int i = 0; i < 10; i++) {
            requestBody.setCompany_name(faker.company().name());
            requestBody.setSeller_name(faker.name().fullName());
            requestBody.setEmail(faker.internet().emailAddress());
            requestBody.setPhone_number(faker.phoneNumber().phoneNumber());
            requestBody.setAddress(faker.address().fullAddress());


            Response response = RestAssured.given().auth().oauth2(token)
                    .contentType(ContentType.JSON).body(requestBody).post(url);
            System.out.println("status code : " + response.statusCode());
            response.prettyPrint();
        }
    }

    @Test
    public void getAllSellersEmails() throws JsonProcessingException {
        String token = CashWiseAutorizationToken.getToken();
        String url = Config.getProperty("cashWiseUrl") + "/api/myaccount/sellers";
        Faker faker = new Faker();

        Map<String,Object> params = new HashMap<>();
        params.put("isArchived",false);
        params.put("page",2);
        params.put("size",10);

        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);

        System.out.println("status code : " + response.statusCode());

        ObjectMapper mapper = new ObjectMapper();

        CustomResponses customResponses = mapper.readValue(response.asString(),CustomResponses.class);

        int size = customResponses.getResponses().size();

        for (int i = 0; i < size ; i ++){
            System.out.println("user's email " + customResponses.getResponses().get(i).getEmail());
        }

    }

    @Test
    public void getSeller (){
        String path = "/api/myaccount/sellers/535";

        ApiRunner.runGet(path);
        System.out.println("seller's name : " + ApiRunner.getCustomResponses().getSeller_name());
        System.out.println("seller's email : " + ApiRunner.getCustomResponses().getEmail());

    }

    @Test
    public void getSellersList (){
        String path = "/api/myaccount/sellers/535";
        Map <String, Object> params = new HashMap<>();
        params.put("isArchived",false);
        params.put("page",1);
//    params.put("size",1);

        ApiRunner.runGet(path,params);

        System.out.println(ApiRunner.getCustomResponses().getResponseBody());
        // get company name of each seller
        // use loop , use custom responce class to get company_name
        // print total number of sellers you can add counter
    }
}




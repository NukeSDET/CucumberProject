package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

public class Regres {
    public static void main(String[] args) {
        Response response = RestAssured.get("https://reqres.in/api/users/2");
        System.out.println("status code" + response.statusCode());
        response.prettyPrint();
        String email = response.jsonPath().get("data.email").toString();
        String firstName = response.jsonPath().get("data.first_name").toString();
        String lastName =response.jsonPath().get("data.last_name").toString();
        String avatar =response.jsonPath().get("data.avatar").toString();
        System.out.println("email is " + email);
        System.out.println("first name  " + firstName);
        System.out.println("last name" + lastName);
        System.out.println("avatar is  " + avatar);
        Assert.assertFalse("email is empty",email.isEmpty());
        System.out.println("email is not empty , it is "+ email);
        Assert.assertFalse("first name is empty" , firstName.isEmpty());
        System.out.println("first name is not empty it is: " + firstName);
        Assert.assertFalse("last name name is empty" , lastName.isEmpty());
        System.out.println("last name  is not empty it is: " + lastName);
        Assert.assertFalse("avatar is empty" , avatar.isEmpty());
        System.out.println("avatar is not empty it is: " + avatar);
        System.out.println("-------------");
        Assert.assertTrue("avatar is not jog.or .png",avatar.endsWith("jog") || avatar.endsWith(".png"));
        System.out.println("avatar is jog. or .png");
        Assert.assertTrue("email does not have reqres.in",email.endsWith("reqres.in"));
        System.out.println("email has reqres.in");




    }
    //assert that fields are not empty and

}

package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashWiseAutorizationToken;

public class CashWiseBankAccountTest {
   @Test
    public void getAllBankAccount(){
       String token = CashWiseAutorizationToken.getToken();
       Response response= RestAssured.given().auth().oauth2(token)
               .get("https://backend.cashwise.us/api/myaccount/bankaccount");
       System.out.println("status code " + response.statusCode());
       response.prettyPrint();

       String bankAccountName = response.jsonPath().getString("[" + 0 + "].bank_account_name");
       System.out.println("bank account : " + bankAccountName);
       String description = response.jsonPath().getString("[" + 0 +"].description");
       String typeOfPay = response.jsonPath().getString("[" + 0 +"].type_of_pay");
       String balance = response.jsonPath().getString("[" + 0 +"].balance");

       String bankAccountItself = response.jsonPath().getString("["+ 0 +"].bank_account_name");

       System.out.println("first bank account name "+ bankAccountItself);
       System.out.println("first bank account description "+description);
       System.out.println("first bank account type pf pay "+ typeOfPay);
       System.out.println("first bank account name balance " + balance);
       System.out.println("------------------");

      Assert.assertFalse("bank account is empty" ,bankAccountItself.trim().isEmpty());
      Assert.assertFalse("description is empty" ,description.trim().isEmpty());
      Assert.assertFalse("type of pay is empty" ,typeOfPay.trim().isEmpty());
      Assert.assertFalse("balance is empty" ,balance.trim().isEmpty());

        int size = response.jsonPath().getInt("$.size()");
      System.out.println("size" + size);

      for (int i = 0 ; i <size; i ++ ){
         String bankName =response.jsonPath().getString("["+ i + "].bank_account_name");
         String description1 = response.jsonPath().getString("["+ i + "].description");
        String typeOfPay1 =response.jsonPath().getString("["+ i + "].type_of_pay");
         String balance1 = response.jsonPath().getString("["+ i + "].balance");
         System.out.println("----------------------");
         Assert.assertFalse("bank account is empty" + i,bankName.trim().isEmpty());
         Assert.assertFalse("description is empty"+ i,description1.trim().isEmpty());
         Assert.assertFalse("type of pay is empty" ,typeOfPay1.trim().isEmpty());
         Assert.assertFalse("balance is empty" ,balance1.trim().isEmpty());
      }



   }
}

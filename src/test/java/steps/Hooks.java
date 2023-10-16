package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utilities.Driver;

public class Hooks {
@Before
    public void beforeScenario(){
    System.out.println("My before method");
}
@After
    public void afterScenario(){

    Driver.closeDriver();
    System.out.println("closed window");
}
}

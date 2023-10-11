package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
@Before
    public void beforeScenario(){
    System.out.println("My before method");
}
@After
    public void afterScenario(){
    System.out.println("My after method");
}
}

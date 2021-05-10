package com.runnerfiles;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features={"features\\Login_Order.feature"},
						glue={"com\\gluefiles"},
						plugin={"pretty","html:target/htmlreports"})

public class Runnerclass {

}
//,"features\\firefox.feature"
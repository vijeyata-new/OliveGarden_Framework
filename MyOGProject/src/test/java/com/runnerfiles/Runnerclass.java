package com.runnerfiles;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features={"features\\fOliv.feature"},
						glue={"com\\gluefiles"},
						plugin={"pretty","html:target/htmlreports"})
public class Runnerclass {

}

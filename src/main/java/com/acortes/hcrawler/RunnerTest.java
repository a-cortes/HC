package com.acortes.hcrawler;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Features",
		glue = "com.acortes.hcrawler.steps",
		monochrome = true
		//,tags={"@Vuelo,@Paquete"}
		)
public class RunnerTest {
	
}

package com.BBBY.DataSimulator.features_api;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber", "json:target/cucumber.json" }, tags = { "@tag1",
		"~@ignore" })
public class ITRunCukes {

}

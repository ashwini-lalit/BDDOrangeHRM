package com.digite.core;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * The TestRunner class is used to configure and run Cucumber tests.
 * It specifies the location of feature files, step definitions, and various
 * reporting options.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.digite.stepdefinitions",  // This is important - points to package with step definitions
    tags = "@Login",
    plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt",
    },
    monochrome = true
)
public class TestRunner {
}

package com.real_estates.hcrawler;

import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.function.Function;

public interface TaskSubmitter {
    List<Function<WebDriver, List<Property>>> getTasks();
}

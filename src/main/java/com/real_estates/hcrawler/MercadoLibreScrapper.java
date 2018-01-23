package com.real_estates.hcrawler;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MercadoLibreScrapper implements TaskSubmitter {

    private String rootUrl;
    private WebDriver driver;

    public MercadoLibreScrapper(String rootUrl, WebDriver driver) {
        this.rootUrl = rootUrl;
        this.driver = driver;
    }

    @Override
    public List<Function<WebDriver, List<Property>>> getTasks() {
        driver.get(rootUrl);

        //TODO: Add error handling (Element not found)
        int entriesPerPage = getEntries(driver).size();
        float totalEntries = getResultsCount(driver);

        int totalPages = (int) Math.ceil(totalEntries / entriesPerPage);

        List<Function<WebDriver, List<Property>>> tasks = new ArrayList<>();
        for (int i=0; i < totalPages; i++) {
            final int entryCount = entriesPerPage * i + 1;

            tasks.add((webDriver) -> {
                String url = String.format("%s_Desde_%d", rootUrl, entryCount);

                webDriver.get(url);
                List<WebElement> entries = getEntries(webDriver);

                return entries
                        .stream()
                        .map(this::entryToProperty)
                        .collect(Collectors.toList());
            });
        }

        return tasks;
    }

    //TODO: Move selectors to config file
    private Property entryToProperty(WebElement entry) {
        Property property = new Property();

        WebElement link = entry.findElement(By.className("item__info-link "));
        property.setUrl(link.getAttribute("href"));

        WebElement price = link.findElement(By.className("price__fraction"));
        String priceText = price.getText().replace(",", "");
        property.setPrice(Float.valueOf(priceText));

        WebElement addressElement = link.findElement(By.className("item__title"));
        property.setAddress(parseAddress(addressElement.getText()));

        //TODO: Remove hardcoding
        property.setSource("Mercado Libre");

        return property;
    }

    private Property.Address parseAddress(String text) {
        String[] parts = text.split("-");

        Property.Address address = new Property.Address();
        //TODO: Remove hardcoding
        address.setCountry("Mexico");

        if (parts.length == 4) {
            address.setState(parts[3]);
            address.setTown(parts[2]);
            address.setSuburb(parts[1]);
        } else if (parts.length == 3) {
            address.setState(parts[2]);
            address.setTown(parts[1]);
            address.setSuburb(parts[0]);
        } //TODO else: throw error/notify

        return address;
    }

    private int getResultsCount(WebDriver driver) {
        String results = driver
                .findElement(By.className("quantity-results"))
                .getText()
                .replace(",", "")
                .split(" ")[0];
        return Integer.valueOf(results);
    }

    private List<WebElement> getEntries(WebDriver driver) {
        return driver.findElements(
                By.className("results-item"));
    }
}

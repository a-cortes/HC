package com.realestate.hcrawler.orchestration;

import com.realestate.hcrawler.MercadoLibreScrapper;
import com.realestate.hcrawler.Property;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;

public class Orchestrator {
    public static void run(int maxInstances) {
        ExecutorService executor = Executors.newFixedThreadPool(maxInstances);
        CompletionService<List<Property>> completionService =
                new ExecutorCompletionService<>(executor);

        //TODO: Get list of sources and construct the beans (Scrapper Builder)
        List<Function<WebDriver, List<Property>>> tasks = new ArrayList<>();

        WebDriverFactory.instantiateDriverObject();
        WebDriver setupDriver = WebDriverFactory.getDriver();

        // Join all tasks from all scrappers
        tasks.addAll(new MercadoLibreScrapper(
                "https://inmuebles.mercadolibre.com.mx/casas/venta/baja-california/tijuana/",
                setupDriver).getTasks());
        
        tasks.addAll(new MercadoLibreScrapper(
                "https://inmuebles.mercadolibre.com.mx/casas/venta/queretaro/queretaro/",
                setupDriver).getTasks());

        System.out.printf("Working with %d tasks \n", tasks.size());

        tasks.forEach(task -> completionService.submit(() -> {
            WebDriverFactory.clearCookies();

            WebDriver driver = WebDriverFactory.getDriver();

            return task.apply(driver);
        }));

        int totalTasks = tasks.size();
        int completed = 0;
        List<Property> properties = new ArrayList<>();
        try {
            while (completed < totalTasks) {
                Future<List<Property>> result = completionService.take();
                properties.addAll(result.get());
                completed ++;
            }

            System.out.printf("Got %d properties \n", properties.size());

            WebDriverFactory.closeDriverObjects();

            //TODO: FIX program not terminating, needed to put this line but its not correct
            System.exit(0);

            //TODO: Implement real exception handling
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

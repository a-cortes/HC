package com.real_estates.orchestration;

import com.real_estates.hcrawler.MercadoLibreScrapper;
import com.real_estates.hcrawler.Property;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

public class Orchestrator {
    public static void run(int maxInstances) {
        ExecutorService executorService = Executors.newFixedThreadPool(maxInstances);

        //TODO: Get list of sources and construct the beans (Scrapper Builder)
        List<Function<WebDriver, List<Property>>> tasks = new ArrayList<>();

        WebDriver setupDriver = WebDriverPoolService.get();
        //Join all tasks from all scrappers
        tasks.addAll(new MercadoLibreScrapper(
                "https://inmuebles.mercadolibre.com.mx/casas/venta/baja-california/tijuana/",
                setupDriver).getTasks());
        
        tasks.addAll(new MercadoLibreScrapper(
                "https://inmuebles.mercadolibre.com.mx/casas/venta/queretaro/queretaro/",
                setupDriver).getTasks());

        WebDriverPoolService.release(setupDriver);

        List<Property> properties = new ArrayList<>();
        tasks.forEach(t -> {
            Future<List<Property>> future = executorService.submit(() -> {
                long delta = System.currentTimeMillis();

                WebDriver driver = WebDriverPoolService.get();

                List<Property> result = t.apply(driver);

                System.out.printf(
                        "Got %d properties in %d milis",
                        result.size(),
                        System.currentTimeMillis() - delta);

                WebDriverPoolService.release(driver);

                return result;
            });

            //TODO: Implement real error handling
            try {
                properties.addAll(future.get());
                System.out.println("Properties so far: " + properties.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}

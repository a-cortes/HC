package com.realestate.hcrawler.orchestration;

import com.real_estates.util.CheckedFunction;
import com.realestate.hcrawler.MercadoLibreScrapper;
import com.realestate.hcrawler.Property;
import com.realestate.hcrawler.webcontext.HtmlUnitRequester;
import com.realestate.hcrawler.webcontext.Requester;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Orchestrator {
    public static void run(int maxInstances) {
        ExecutorService executor = Executors.newFixedThreadPool(maxInstances);
        CompletionService<List<Property>> completionService =
                new ExecutorCompletionService<>(executor);

        //TODO: Get list of sources and construct the beans (Scrapper Builder)
        List<CheckedFunction<Requester, List<Property>>> tasks = new ArrayList<>();

        // Join all tasks from all scrappers
        try {
            tasks.addAll(new MercadoLibreScrapper(
                    "https://inmuebles.mercadolibre.com.mx/casas/venta/baja-california/tijuana/",
                    new HtmlUnitRequester()).getTasks());
        } catch (Exception e) {
            //TODO: Implement real error handling
            e.printStackTrace();
        }

        System.out.printf("Working with %d tasks \n", tasks.size());

        tasks.forEach(task -> completionService.submit(() -> {
            try {
                return task.apply(new HtmlUnitRequester());
                //TODO: Implement real error handling
            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
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

            executor.shutdown();

            System.out.printf("Got %d properties \n", properties.size());

            //TODO: Implement real exception handling
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

package com.realestate.hcrawler;


import com.realestate.hcrawler.util.CheckedFunction;
import com.realestate.hcrawler.webcontext.Requester;
import com.realestate.hcrawler.webcontext.WebContext;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MercadoLibreScrapper implements TaskSubmitter {

    private String rootUrl;
    private Requester requester;
    private WebContext mainContext;

    public MercadoLibreScrapper(String rootUrl, Requester requester) {
        this.rootUrl = rootUrl;
        this.requester = requester;
    }

    @Override
    public List<CheckedFunction<Requester, List<Property>>> getTasks() throws Exception {
        mainContext = requester.get(rootUrl);

        //TODO: Add error handling (Element not found)
        int entriesPerPage = getEntries(mainContext).size();
        float totalEntries = getResultsCount(mainContext);

        int totalPages = (int) Math.ceil(totalEntries / entriesPerPage);

        List<CheckedFunction<Requester, List<Property>>> tasks = new ArrayList<>();
        for (int i=0; i < totalPages; i++) {
            final int entryCount = entriesPerPage * i + 1;

            tasks.add((requester) -> {
                String url = String.format("%s_Desde_%d", rootUrl, entryCount);
                WebContext context = requester.get(url);
                return getEntries(context)
                        .stream()
                        .map(this::entryToProperty)
                        .collect(Collectors.toList());
            });
        }

        return tasks;
    }

    //TODO: Move selectors to config file
    private Property entryToProperty(WebContext entry) {
        Property property = new Property();

        WebContext link = entry.find(".item__info-link ");
        property.setUrl(link.getAttribute("href"));

        WebContext price = link.find(".price__fraction");
        String priceText = price.getText().replace(",", "");
        property.setPrice(Float.valueOf(priceText));

        WebContext addressElement = link.find(".item__title");
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
            address.setCountry(parts[2]);
            address.setNeighborhood(parts[1]);
        } else if (parts.length == 3) {
            address.setState(parts[2]);
            address.setCountry(parts[1]);
            address.setNeighborhood(parts[0]);
        } //TODO else: throw error/notify

        return address;
    }

    private int getResultsCount(WebContext context) {
        String results = context
                .find(".quantity-results")
                .getText();
        Matcher matcher = Pattern.compile("\\d+").matcher(results);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(0));
        } //TODO: else throw error
        else {
            return 0;
        }
    }

    private List<WebContext> getEntries(WebContext context) {
        return context.findAll(".results-item");
    }
}

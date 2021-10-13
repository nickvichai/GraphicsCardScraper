package scraper;

import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Node;


public final class App {
    private App() {
    }
    public static void main(String[] args) throws IOException {

        // List of variables 
        ArrayList<Double> arrLiOfPrices = new ArrayList<>();
        Double[] arrOfPrices;
        ArrayList<String> arrLiOfNames = new ArrayList<>();
        String[] arrOfNames;
        ArrayList<String> arrLiOfUrl = new ArrayList<>();
        String[] arrOfUrl;
        ArrayList<Boolean> arrLiOfInStock = new ArrayList<>();
        Boolean[] arrOfInStock;
        String link = "https://www.newegg.ca/p/pl?d=3070&N=100007708%20601357250&isdeptsrh=1";

        // Create connections to link and grab all item cells from webpage
        Document doc = Jsoup.connect(link).get();
        log(doc.title());
        Elements cells = doc.select(".item-cell");

        // Collect Graphics Cards name and price
        for (Element cell : cells) {
            Elements prices = doc.select(".price-current");
            Elements items = doc.select(".item-title");
            Elements itemUrl = doc.select(".item-img");

            // Retrieve name of Graphics card
            arrLiOfNames = getNameArray(items);

            // Convert to Array
            arrOfNames = arrLiOfNames.toArray(new String[arrLiOfNames.size()]);

            // Retrieve price of Graphics card
            arrLiOfPrices = getPriceArray(prices);

            // Retrieve link of Graphics Card and in stock data
            for (Element item : itemUrl) {
                // Retrieve url
                String urlString = item.attr("href");
                arrLiOfUrl.add(urlString);
                
                // Retrieve in stock data
                Document itemLink = Jsoup.connect(urlString).get();
                log(itemLink.title());
                Elements stock = itemLink.select(".product-inventory");
                String stockString = stock.text();
                Boolean isInStock;
                if (stockString.equals("In stock.")) {
                    isInStock = true;
                    arrLiOfInStock.add(isInStock);
                }
                else {
                    isInStock = false;
                    arrLiOfInStock.add(isInStock);
                }
            }

            // Convert Names ArrayList to Array
            arrOfNames = arrLiOfNames.toArray(new String[arrLiOfNames.size()]);

            // Convert Prices ArrayList to Array
            arrOfPrices = arrLiOfPrices.toArray(new Double[arrLiOfPrices.size()]);

            // Convert url ArrayList to Array
            arrOfUrl = arrLiOfUrl.toArray(new String[arrLiOfUrl.size()]);

            // Convert InStock ArrayList to Array
            arrOfInStock = arrLiOfInStock.toArray(new Boolean[arrLiOfInStock.size()]);

            for (int i = 0; i < arrOfNames.length; i++) {
                Item card = new Item();
                card.setItemName(arrOfNames[i]);
                card.setPrice(arrOfPrices[i]);
                card.setUrl(arrOfUrl[i]);
                card.setInStock(arrOfInStock[i]);

                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(card);

                System.out.println(jsonString + "\n");
            }
            break;
        }


    }
    private static void log(String title) {
    }

    public static ArrayList<Double> getPriceArray(Elements prices) {
        Double price;
        ArrayList<Double> arrOfPrices = new ArrayList<>();
        for (Element amount : prices) {
            String toString = amount.text();
            for (int i = 0; i < toString.length(); i++) {
                char c = toString.charAt(i);
                if (Character.isWhitespace(c)) {
                    toString = toString.substring(0, i);
                }
            }
            String[] arrOfStr = toString.split("[$,]");
            String pull = "";
            for (String val : arrOfStr) {
                pull = pull + val;
            }
            price = Double.parseDouble(pull);
            arrOfPrices.add(price);
        }
        return arrOfPrices;
    }

    public static ArrayList<String> getNameArray(Elements items) {
        ArrayList<String> arrOfNames = new ArrayList<>();
        String itemName;
        for (Element name : items) {
            itemName = name.text();
            arrOfNames.add(itemName);
        }
        return arrOfNames;
    }

}

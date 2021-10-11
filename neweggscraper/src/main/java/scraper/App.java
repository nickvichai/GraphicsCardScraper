package scraper;

import java.util.Scanner;
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
        ArrayList<Double> arrLiOfPrices = new ArrayList<>();
        Double[] arrOfPrices;
        ArrayList<String> arrLiOfNames = new ArrayList<>();
        String[] arrOfNames;
        String itemName, url;
        Double price;
        Boolean inStock;
        String link = "https://www.newegg.ca/p/pl?d=3070&N=100007708%20601357250&isdeptsrh=1";

        Document doc = Jsoup.connect(link).get();
        log(doc.title());
        Elements cells = doc.select(".item-cell");
        for (Element cell : cells) {
            Item graphicsCard = new Item();
            Elements prices = doc.select(".price-current");
            Elements items = doc.select(".item-title");
            // Retrieve name of graphics card
            arrLiOfNames = getNameArray(items);
            arrOfNames = arrLiOfNames.toArray(new String[arrLiOfNames.size()]);
            arrLiOfPrices = getPriceArray(prices);
            arrOfPrices = arrLiOfPrices.toArray(new Double[arrLiOfPrices.size()]);
            for (int i = 0; i < arrOfNames.length; i++) {
                System.out.println((i + 1) + " " + arrOfNames[i] + " | " + "Price: $" + arrOfPrices[i]);
            }
            break;
        }
        Elements ahref = doc.select(".item-img");
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

package scraper;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public final class App {
    private App() {
    }
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://www.newegg.ca/evga-geforce-rtx-3070-08g-p5-3767-kl/p/1FT-001K-00DH0?Description=3070&cm_re=3070-_-1FT-001K-00DH0-_-Product").get();
        log(doc.title());
        Elements graphicsCard = doc.select(".page-section-inner");
        System.out.println(graphicsCard);
    }
    private static void log(String title) {
    }

}

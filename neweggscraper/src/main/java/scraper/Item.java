package scraper;

public class Item {
    private String url;
    private String itemName;
    private Double price;
    private Boolean inStock;

    // Constructor with parameters
    public Item(String url, String itemName, Double price, Boolean inStock) {
        this.url = url;
        this.itemName = itemName;
        this.price = price;
        this.inStock = inStock;
    }

    // Non-parameter constructor to create new Item object and use getters/setters to set properties
    public Item() {

    }

    // Getters to return information of item to object
    public String getItemName() {
        return itemName;
    }

    public String getUrl() {
        return url;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getInStock() {
        return inStock;
    }

    // Setters to set properties of item
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }
}

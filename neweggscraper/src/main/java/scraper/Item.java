package scraper;

public class Item {
    private String url;
    private String itemName;
    private Double price;
    private String specs;
    private Boolean inStock;

    public Item(String url, String itemName, Double price,
                String specs, Boolean inStock) {
        
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

    public String getSpecs() {
        return specs;
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

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }
}

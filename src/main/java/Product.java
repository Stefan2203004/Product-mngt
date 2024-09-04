import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private double price;
    private String image;
    private String description;

    public Product(Long id, String name, double price, String image, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
    }
}

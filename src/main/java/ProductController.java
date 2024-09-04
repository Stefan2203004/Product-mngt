import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    private Map<Long, Product> products = new HashMap<>();
    private long idCounter = 1;
    Gson gson = new Gson();
    
    {{ products.put(1L, new Product(1L, "Laptop", 999.99, "laptop.jpg", "A high-performance laptop."));
    products.put(2L, new Product(2L, "Stefin Laptop", 999.99, "laptop.jpg", "A high-performance laptop."));   }} 
    
    // Handle IllegalArgumentException specifically
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    // POST method to add product
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        product.setId(idCounter++);
        products.put(product.getId(), product);
        return product;
    }

    // POST method to read the product
    @GetMapping("/get")
    public String getProducts(@RequestParam Long id){
        System.out.println(products.get(id));
        
        Product prod = products.get(id);

        String jsonString = gson.toJson(prod);

        return jsonString;
    }

    // GET method to delete the product
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam Long id){
        if (products.containsKey(id)){
            products.remove(id);
            return "Product deleted";
        }else{
            return "Product not found";
        }
    }

    // PUT method to modify the product
    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @RequestBody Product newProduct){
        if (products.containsKey(id)){
            newProduct.setId(id);
            products.put(id, newProduct);
            return "Product updated";
        }else {
            return "Product not found";
        }
    }
}

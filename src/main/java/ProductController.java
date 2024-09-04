import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    private Map<Long, Product> products = new HashMap<>();
    private long idCounter = 1;

    // POST method to add product
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        product.setId(idCounter++);
        products.put(product.getId(), product);
        return product;
    }

    // POST method to read the product
    @PostMapping("/get")
    public Product getProducts(@RequestBody Long id){
        return products.get(id);
    }

    // GET method to delete the product
    @GetMapping("/delete")
    public String deleteProduct(@RequestBody Long id){
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

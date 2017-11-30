package ua.ali_x.spring.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.ali_x.spring.model.Category;
import ua.ali_x.spring.model.Product;
import ua.ali_x.spring.service.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/product")
public class ProductController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getProducts() {
        return productService.getAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product,
                              @RequestBody Category category,
                              @RequestParam("file") MultipartFile file) {
        product.setCategory(category);
        product.setRating(0);
        productService.create(product);
        imageService.saveImage(Integer.toString(product.getId()), file);
        return product;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product product, @RequestBody Category category) {
        product.setCategory(category);
        productService.create(product);
        return product;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delProduct(@RequestBody Product product) {
        productService.delete(product);
    }
}

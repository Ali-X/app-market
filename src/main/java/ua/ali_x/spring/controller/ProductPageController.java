package ua.ali_x.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.ali_x.spring.model.Product;
import ua.ali_x.spring.model.User;
import ua.ali_x.spring.service.ImageService;
import ua.ali_x.spring.service.OrderService;
import ua.ali_x.spring.service.ProductService;
import ua.ali_x.spring.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
@RequestMapping(value = "/product")
public class ProductPageController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Product getProductById(@RequestParam("p_id") Integer idProduct) {
        return productService.getProduct(idProduct);
    }

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public void addToCart(@RequestBody Product product, HttpServletRequest request) {
        User user = userService.getByUserName(request.getRemoteUser());
        if (user != null) {
            product.incrementRating();
            productService.update(product);
            orderService.addOrder(user, product);
        }
    }

    @RequestMapping(value = "/image", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] image(@RequestParam("id") String p_id) throws IOException {
        return imageService.getImage(p_id);
    }

}

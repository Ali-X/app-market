package ua.ali_x.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.ali_x.spring.model.Product;
import ua.ali_x.spring.model.User;
import ua.ali_x.spring.service.CategoryService;
import ua.ali_x.spring.service.OrderService;
import ua.ali_x.spring.service.ProductService;
import ua.ali_x.spring.service.UserService;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/product")
    public ModelAndView product(@RequestParam("c_id") Integer idCategory,
                                @RequestParam("p_id") Integer idProduct){
        ModelAndView mv = new ModelAndView("product", "product", productService.getProduct(idCategory, idProduct));
        return mv;
    }

    @RequestMapping(value = "/admin/product/add", method = RequestMethod.POST)
    public ModelAndView addProduct(@ModelAttribute("product") Product product, @RequestParam("c_id") Integer id) {
        productService.create(product.getName(), product.getDescription(), id, product.getPrice());
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/admin/product/upd", method = RequestMethod.GET)
    public ModelAndView getUpdateProduct(@RequestParam("p_id") Integer id) {
        ModelAndView mv = new ModelAndView("editProduct", "product", new Product());
        mv.addObject("id", id);
        mv.addObject("categories", categoryService.getAll());
        return mv;
    }

    @RequestMapping(value = "/admin/product/upd", method = RequestMethod.POST)
    public ModelAndView updateProduct(@ModelAttribute("product") Product product, @RequestParam("id") Integer id,
                                      @RequestParam("c_name") String catname) {
        productService.update(id, product.getName(), product.getDescription(), product.getPrice(), catname);
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/admin/product/del", method = RequestMethod.GET)
    public ModelAndView delProduct(@RequestParam("p_id") Integer id) {
        productService.delete(id);
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/product/addtocart", method = RequestMethod.GET)
    public ModelAndView addToCart(@RequestParam("p_id") Integer p_id,
                                  @CookieValue(value = "token", defaultValue = "token") String token){
        ModelAndView mv;
        User userInput = userService.findByToken(token);
        if (userInput != null) {
            orderService.addOrder(userInput.getId(), p_id);
            mv = new ModelAndView("success");
        } else mv = new ModelAndView("login", "user", new User());
        return mv;
    }

}

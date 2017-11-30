package ua.ali_x.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.ali_x.spring.model.Category;
import ua.ali_x.spring.model.Product;
import ua.ali_x.spring.model.User;
import ua.ali_x.spring.service.*;

import javax.servlet.http.Cookie;
import java.util.List;

@RestController
@RequestMapping(value = "/home")
public class HomePageController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ImageService imageService;

    @Value("${user.role.default}")
    private String userRoleDefault;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public @ResponseBody List<Category> getCategories() {
        return categoryService.getAll();
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public List<Product> getProductsByCategoryId(@RequestParam("c_id") Integer idCategory){
        return productService.getAll(idCategory);
    }

    @RequestMapping(value = "/products/top", method = RequestMethod.GET)
    public List<Product> getTopProduct(){
        return productService.getTopProducts();
    }

    @RequestMapping(value = "/product/find/name", method = RequestMethod.GET)
    public List<Product> getProductByName(@RequestParam("name") String name){
        return productService.getByName(name);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public User registerUser(@RequestBody User user,
                              @RequestParam("file") MultipartFile file) {
        String token = user.getUsername() + System.nanoTime();
        user.setRole(roleService.getRoleByRoleName(userRoleDefault));
        user.setToken(token);
        user.setEnabled(Boolean.TRUE);
        userService.create(user);
        imageService.saveImage(user.getUsername(), file);
        return user;
    }
}

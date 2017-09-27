package ua.ali_x.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.ali_x.spring.model.Category;
import ua.ali_x.spring.model.Product;
import ua.ali_x.spring.model.User;
import ua.ali_x.spring.service.CategoryService;
import ua.ali_x.spring.service.ProductService;
import ua.ali_x.spring.service.RoleService;
import ua.ali_x.spring.service.UserService;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/admin/category", method = RequestMethod.GET)
    public ModelAndView category(@RequestParam(value = "page", defaultValue = "-1") Integer pageAttr){
        int page = 1;
        int recordsPerPage = 5;
        int temp;
        if (pageAttr >= 0)
            page = pageAttr;
        List<Category> list = categoryService.getAll();
        List<Category> sublist;
        temp = page * recordsPerPage;
        int noOfRecords = list.size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        if (temp < noOfRecords) {
            sublist = list.subList(temp - recordsPerPage, temp);
        } else {
            sublist = list.subList(temp - recordsPerPage, noOfRecords);
        }
        ModelAndView mv = new ModelAndView("adminCategory", "category", new Category());
        mv.addObject("categories", sublist);
        mv.addObject("noOfPages", noOfPages);
        mv.addObject("currentPage", page);
        return mv;
    }

    @RequestMapping(value = "/admin/product", method = RequestMethod.GET)
    public ModelAndView product(@RequestParam(value = "page", defaultValue = "-1") Integer pageAttr){
        int page = 1;
        int recordsPerPage = 5;
        int temp;
        if (pageAttr >= 0)
            page = pageAttr;
        List<Product> list = productService.getAll();
        List<Product> sublist;
        temp = page * recordsPerPage;
        int noOfRecords = list.size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        if (temp < noOfRecords) {
            sublist = list.subList(temp - recordsPerPage, temp);
        } else {
            sublist = list.subList(temp - recordsPerPage, noOfRecords);
        }
        ModelAndView mv = new ModelAndView("adminProduct", "product", new Product());
        mv.addObject("products", sublist);
        mv.addObject("noOfPages", noOfPages);
        mv.addObject("currentPage", page);
        mv.addObject("categories", categoryService.getAll());
        return mv;
    }

    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public ModelAndView user(){
        ModelAndView mv = new ModelAndView("adminUser", "user", new User());
        mv.addObject("users", userService.getAll());
        mv.addObject("roles", roleService.getAll());
        return mv;
    }

}

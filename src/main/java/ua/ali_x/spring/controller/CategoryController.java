package ua.ali_x.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.ali_x.spring.model.Category;
import ua.ali_x.spring.service.CategoryService;
import ua.ali_x.spring.service.ProductService;

@Controller
public class CategoryController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView categories(){
        ModelAndView mv = new ModelAndView("categories", "categories", categoryService.getAll());
        return mv;
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ModelAndView category(@RequestParam("c_id") Integer idCategory){
        ModelAndView mv = new ModelAndView("category", "products", productService.getAll(idCategory));
        return mv;
    }

    @RequestMapping(value = "/admin/category/add", method = RequestMethod.POST)
    public ModelAndView addCategory(@ModelAttribute("category") Category category) {
        categoryService.create(category.getName());
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/admin/category/upd", method = RequestMethod.GET)
    public ModelAndView getUpdateCategory(@RequestParam("c_id") Integer id) {
        ModelAndView mv = new ModelAndView("editCategory", "category", new Category());
        mv.addObject("id", id);
        return mv;
    }

    @RequestMapping(value = "/admin/category/upd", method = RequestMethod.POST)
    public ModelAndView updateCategory(@ModelAttribute("category") Category category, @RequestParam("id") Integer id) {
        categoryService.update(id, category.getName());
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/admin/category/del", method = RequestMethod.GET)
    public ModelAndView delCategory(@RequestParam("c_id") Integer id) {
        categoryService.delete(id);
        return new ModelAndView("success");
    }
}

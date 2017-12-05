package ua.ali_x.spring.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.ali_x.spring.model.Category;
import ua.ali_x.spring.service.CategoryService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
@RequestMapping(value = "/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public List<Category> getProducts() {
        return categoryService.getAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Category addCategory(@RequestBody Category category) {
        categoryService.create(category);
        return category;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Category updateCategory(@RequestBody Category category) {
        categoryService.update(category);
        return category;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delCategory(@RequestBody Category category) {
        categoryService.delete(category);
    }
}

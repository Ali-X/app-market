package ua.ali_x.spring.service;

import ua.ali_x.spring.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    void create(Category category);

    void delete(Category category);

    void update(Category category);


}

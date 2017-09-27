package ua.ali_x.spring.dao;


import ua.ali_x.spring.model.Category;

import java.util.List;

public interface CategoryDAO {

    void update(Category category);

    void create(Category category);

    void delete(Category category);

    List<Category> getAll();

}

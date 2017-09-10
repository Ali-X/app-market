package ua.ali_x.spring.dao;


import ua.ali_x.spring.model.Category;

import java.util.List;

public interface CategoryDAO {

    void update(Integer id, String new_name);

    void create(String category);

    void delete(Integer id);

    List<Category> getAll();

}

package ua.ali_x.spring.service;

import ua.ali_x.spring.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    void create(String name);

    void delete(Integer id);

    void update(Integer id, String new_name);


}

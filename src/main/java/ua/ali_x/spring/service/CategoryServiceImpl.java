package ua.ali_x.spring.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.ali_x.spring.dao.CategoryDAO;
import ua.ali_x.spring.model.Category;

import java.util.List;
@Service
@Qualifier
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<Category> getAll() {
        return categoryDAO.getAll();
    }

    @Override
    public void create(Category category) {
        categoryDAO.create(category);
    }

    public void delete(Category category) {
        categoryDAO.delete(category);
    }

    @Override
    public void update(Category category) {
        categoryDAO.update(category);
    }
}

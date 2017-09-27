package ua.ali_x.spring.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDAO {

    @Autowired
    protected SessionFactory sessionFactory;

}


package ua.ali_x.spring.service.validation;

public interface Validator<T> {

    boolean validate(T t);

}

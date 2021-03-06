package ua.ali_x.spring.service.validation;

import org.springframework.stereotype.Component;
import ua.ali_x.spring.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserPropertiesValidatorImpl implements UserPropertiesValidator {

    private final Integer PASSWORD_MIN_LENGTH = 6;
    private final String regexForName = "^[A-Za-z0-9]{4,30}$";
    private Pattern SUPPORTED_CHARACTERS = Pattern.compile(regexForName);

    @Override
    public boolean validate(User user) {
        String userName = user.getUsername();
        String userPassword = user.getPassword();
        Matcher nameMatcher = SUPPORTED_CHARACTERS.matcher(userName);
        if(!nameMatcher.find()) {
            throw new RuntimeException("Username is not valid: " + userName);
        }
        if(userPassword.length() < PASSWORD_MIN_LENGTH) {
            throw new RuntimeException("Password is too short: " + userPassword);
        }
        return true;
    }
}

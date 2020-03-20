package vadim.volin.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import vadim.volin.model.User;
import vadim.volin.services.UserService;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        User fromDB = userService.getByUserMail(user.getUsermail());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usermail", "NotEmpty");

        if (fromDB == null) {
            errors.rejectValue("usermail", "user not found");
            return;
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Incorrect password");
        }

        if (!user.getPassword().equals(fromDB.getPassword())) {
            errors.rejectValue("password", "Incorrect password");
        }
    }

}


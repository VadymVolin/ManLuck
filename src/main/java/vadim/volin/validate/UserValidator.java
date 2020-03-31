package vadim.volin.validate;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import vadim.volin.model.User;
import vadim.volin.services.UserService;

@Component
public class UserValidator implements Validator {

    private static final Logger logger = Logger.getLogger(UserValidator.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        User fromDB = userService.getByUserName(user.getUsername());
        ValidationUtils.rejectIfEmpty(errors, "username", "NotEmpty");

        if (fromDB == null) {
            logger.warn("user not found in manluck.user");
            System.out.println("user not found in manluck.user: " + user);
            errors.rejectValue("username", "user_not_found");
        }

        ValidationUtils.rejectIfEmpty(errors, "password", "NotEmpty");
        if (!user.getPassword().equals(fromDB.getPassword())) {
            logger.warn("password user inconfirm");
            System.out.println("password user invalid: " + fromDB.getPassword() + " : " + user.getPassword());
            errors.rejectValue("password", "incorrect_password");
        }
    }

}


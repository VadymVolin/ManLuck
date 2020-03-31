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
        System.out.println("USERVALIDATION");
        ValidationUtils.rejectIfEmpty(errors, "username", "NotEmpty");

        if (fromDB == null) {
            logger.warn("user not found in manluck.user");
            errors.rejectValue("username", "user not found");
            return;
        }

        ValidationUtils.rejectIfEmpty(errors, "password", "NotEmpty");
        if (user.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,34}$")) {
            logger.warn("password user invalid");
            errors.rejectValue("password", "Incorrect password");
        }

//        if (!user.getPassword().equals(fromDB.getPassword())) {
//            logger.warn("password user inconfirm");
//            errors.rejectValue("password", "Incorrect password");
//        }
    }

}


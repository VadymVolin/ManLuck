package vadim.volin.services;

public interface SecurityService {
    String findLoggedInUsermail();

    void autologin(String usermail, String password);
}
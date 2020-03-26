package vadim.volin.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "user", schema = "manluck")
@Component
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Integer id;
    @Column(name = "usermail", unique = true, nullable = false)
    @NotNull
    private String usermail;
    @Column(name = "password", nullable = false)
    @NotNull
    private String password;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "userphone")
    private String userphone;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "company")
    private String company;
    @Column(name = "position")
    private String position;

    public User() {
    }

    public User(int id, String usermail, String password) {
        this.id = id;
        this.usermail = usermail;
        this.password = password;
    }

    public User(int id, String usermail, String password, String username,
                String userphone, String country, String city,
                String company, String position) {
        this.id = id;
        this.usermail = usermail;
        this.password = password;
        this.username = username;
        this.userphone = userphone;
        this.country = country;
        this.city = city;
        this.company = company;
        this.position = position;
    }

    public User(String usermail, String password) {
        this.usermail = usermail;
        this.password = password;
    }

    public User(String usermail, String password, String username,
                String userphone, String country, String city,
                String company, String position) {
        this.usermail = usermail;
        this.password = password;
        this.username = username;
        this.userphone = userphone;
        this.country = country;
        this.city = city;
        this.company = company;
        this.position = position;
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getUsermail() != null ? !getUsermail().equals(user.getUsermail()) : user.getUsermail() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
            return false;
        if (getUserphone() != null ? !getUserphone().equals(user.getUserphone()) : user.getUserphone() != null)
            return false;
        if (getCountry() != null ? !getCountry().equals(user.getCountry()) : user.getCountry() != null) return false;
        if (getCity() != null ? !getCity().equals(user.getCity()) : user.getCity() != null) return false;
        if (getCompany() != null ? !getCompany().equals(user.getCompany()) : user.getCompany() != null) return false;
        return getPosition() != null ? getPosition().equals(user.getPosition()) : user.getPosition() == null;
    }

    @Override
    public int hashCode() {
        int result = getUsermail() != null ? getUsermail().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getUserphone() != null ? getUserphone().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getCompany() != null ? getCompany().hashCode() : 0);
        result = 31 * result + (getPosition() != null ? getPosition().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "mail='" + usermail + '\'' +
                ", password='" + password + '\'' +
                ", name='" + username + '\'' +
                ", phone='" + userphone + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

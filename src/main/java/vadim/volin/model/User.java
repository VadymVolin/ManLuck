package vadim.volin.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user", schema = "manluck", uniqueConstraints = @UniqueConstraint(columnNames = "usermail"))
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Integer id;
    @Email
    @Column(name = "usermail", unique = true, nullable = false)
    @NotNull
    private String usermail;
    @Column(name = "password", nullable = false, scale = 34)
    @NotNull
    private String password;
    @Transient
    private String confirmPassword;
    @Column(name = "username", unique = true, nullable = false)
    @NotNull
    private String username;
    @Column(name = "user_img")
    private String user_img;
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
    @Column(name = "active")
    private Boolean active;
    @Column(name = "roles")
    private String roles;
    @Column(name = "user_tasks_json")
    private String userTasksJson;


    public User() {
    }

    public User(@NotNull int id, @NotNull String usermail, @NotNull String password) {
        this.id = id;
        this.usermail = usermail;
        this.password = password;
    }

    public User(@NotNull int id, @NotNull String usermail, @NotNull String password, @NotNull String username,
                String userphone, String country, String city,
                String company, String position, boolean active, String roles, String userTasksJson) {
        this.id = id;
        this.usermail = usermail;
        this.password = password;
        this.username = username;
        this.userphone = userphone;
        this.country = country;
        this.city = city;
        this.company = company;
        this.position = position;
        this.roles = roles;
        this.active = active;
        this.userTasksJson = userTasksJson;
    }

    public User(@NotNull String usermail, @NotNull String password) {
        this.usermail = usermail;
        this.password = password;
    }

    public User(@NotNull String usermail, @NotNull String password, @NotNull String username,
                String userphone, String country, String city,
                String company, String position, boolean active, String roles, String userTasksJson) {
        this.usermail = usermail;
        this.password = password;
        this.username = username;
        this.userphone = userphone;
        this.country = country;
        this.city = city;
        this.company = company;
        this.position = position;
        this.active = active;
        this.roles = roles;
        this.userTasksJson = userTasksJson;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUserTasksJson() {
        return userTasksJson;
    }

    public void setUserTasksJson(String userTasksJson) {
        this.userTasksJson = userTasksJson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (usermail != null ? !usermail.equals(user.usermail) : user.usermail != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (confirmPassword != null ? !confirmPassword.equals(user.confirmPassword) : user.confirmPassword != null)
            return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (userphone != null ? !userphone.equals(user.userphone) : user.userphone != null) return false;
        if (country != null ? !country.equals(user.country) : user.country != null) return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;
        if (company != null ? !company.equals(user.company) : user.company != null) return false;
        if (position != null ? !position.equals(user.position) : user.position != null) return false;
        if (active != null ? !active.equals(user.active) : user.active != null) return false;
        if (userTasksJson != null ? !userTasksJson.equals(user.userTasksJson) : user.userTasksJson != null)
            return false;
        return roles != null ? roles.equals(user.roles) : user.roles == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (usermail != null ? usermail.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (confirmPassword != null ? confirmPassword.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (userphone != null ? userphone.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        result = 31 * result + (userTasksJson != null ? userTasksJson.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", usermail='" + usermail + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", username='" + username + '\'' +
                ", userphone='" + userphone + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", active=" + active +
                ", roles='" + roles + '\'' +
                ", userTasksJson='" + userTasksJson + '\'' +
                '}';
    }
}

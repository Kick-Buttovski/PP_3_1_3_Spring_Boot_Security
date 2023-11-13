package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
@Component
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z\\-_]+$", message = "The name can only contain letters, dashes, and underscores.")
    @Size(min = 3, max = 20, message = "The name must be between 5 and 20 characters long")
    private String username;

    @Column
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 200, message = "The password length must be from 3 to 200 characters")
    private String password;

    @Column
    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-Z\\-]+$", message = "The name can only keep letters and a dash symbol.")
    @Size(min = 1, max = 50, message = "The name must be between 1 and 50 characters long")
    private String name;

    @Column
    @NotBlank(message = "Surname is required")
    @Pattern(regexp = "^[a-zA-Z\\-]+$", message = "The family can only keep letters and a dash symbol.")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters long")
    private String surname;

    @Column
    @Digits(integer = 3, fraction = 0, message = "Age must be a numeric value")
    @NotNull(message = "Age cannot be empty")
    @Min(value = 1, message = "Age must be at least 1")
    @Max(value = 100, message = "Age must be at most 100")
    private Integer age;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;


    public User() {

    }

    public User(String username, String password, String name, String surname, Integer age, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.roles = roles;
    }

    public User(String username, String password, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(Long id, String username, String password, String name, String surname, Integer age, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public List<Role> getAuthorities() {
        return roles.stream()
                .map(role -> new Role(role.getRole()))
                .collect(Collectors.toList());
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void deleteRole(Role role) {
        this.roles.remove(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public List<String> getStringRoles() {
        List<String> roles = new ArrayList<>();
        for (Role role : this.roles) {
            roles.add(role.toString());
        }
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", roles=" + roles +
                ", isAdmin=" +
                '}';
    }
}

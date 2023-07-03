package com.Spring.SpringBoot.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @Column(name="firstname", nullable = false)
    private String firstname;

    @NotBlank
    @Column(name="lastname", nullable = false)
    private String lastname;

    //email should be a valid email format
    //email should not null or empty
    @NotBlank
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    //password should not be null or empty
    //password should have atleast 8 character
    
    @NotBlank
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,128}$",
            message = "at least one uppercase letter, " +'\'' +
                    "one lowercase letter," +'\'' +
                    " one number and one special character:")
    /*@Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[\\W_])")*/
    private String password;

    @Column(name="is_enabled", updatable = true,nullable = true)
    private boolean isEnabled;

    @NotBlank
    @Column(name="confirm_password", updatable=true)
    private String confirmPassword;

    @Column(name="reset_password_token", updatable = true, nullable = true)
    private String resetPasswordToken;

    @Column(name="role",insertable = true)
    private String roles;

    public User() {

    }

    public User(String firstname, String lastname, String email, String password,boolean isEnabled,String confirmPassword, String roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.isEnabled=isEnabled;
        this.confirmPassword=confirmPassword;
        this.roles=roles;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", Is Enable='" + isEnabled + '\'' +
                ", Confirm Password='" + confirmPassword + '\'' +
                '}';
    }
}

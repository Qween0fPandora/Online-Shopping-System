package model;

public class Customer {
    private String email;
    private String password;
    private String name;
    private String detail;
    private String city;
    private String country;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public Customer(String email, String name, String password, String detail, String city, String country) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.detail = detail;
        this.city = city;
        this.country = country;
    }

}

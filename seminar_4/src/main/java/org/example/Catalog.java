package org.example;

public class Catalog {
    private String id;
    private String name;
    private String experience;
    private String phone;
    public Catalog (String id, String name, String experience, String phone) {
        this.id = id;
        this.name = name;
        this.experience = experience;
        this.phone = phone;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getExperience() {
        return experience;
    }
    public void setExperience(String experience) {
        this.experience = experience;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void Print () {
        System.out.println("Employee -> ID: " + this.id +
                " Name: " + this.name +
                " Experience: " + this.experience +
                " Phone: " + this.phone);
    }
}

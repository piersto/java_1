package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String title;
    private final String company;
    private final String address;
    private final String homephone;
    private final String email;
    private final String group;

    public ContactData(int id, String firstname, String middlename, String lastname, String title, String company, String address, String homephone, String email, String group) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.homephone = homephone;
        this.email = email;
        this.group = group;
    }

    public ContactData(String firstname, String middlename, String lastname, String title, String company, String address, String homephone, String email, String group) {
        this.id = 0;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.homephone = homephone;
        this.email = email;
        this.group = group;
    }

    public int getId() { return id; }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }


    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}

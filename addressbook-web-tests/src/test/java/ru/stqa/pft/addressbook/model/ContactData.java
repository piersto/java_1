package ru.stqa.pft.addressbook.model;

import java.io.File;
import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String firstname;
    private String middlename;
    private String lastname;
    private String title;
    private String company;
    private String address;
    private String homephone;
    private String mobilephone;
    private String workphone;
    private String email;
    private String email2;
    private String email3;
    private String group;
    private String allPhones;
    private String allMails;
    private File photo;

    public File getPhoto() { return photo; }

    public String getAllMails() { return allMails; }

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

    public String getAddress() { return address; }

    public String getHomephone() {
        return homephone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public String getWorkphone() {
        return workphone;
    }

    public String getEmail2() { return email2; }

    public String getEmail3() { return email3; }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public  ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }

    public ContactData withWorkphone(String workphone) {
        this.workphone = workphone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAllMails(String allMails) {
        this.allMails = allMails;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;

    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;

    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;

    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;

    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;

    }

    public ContactData withHomephone(String homephone) {
        this.homephone = homephone;
        return this;

    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;

    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;

    }

    public ContactData withId(int id) {
        this.id = id;
        return this;

    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
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
}

package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;


@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class ContactData {

    @Id
    @Column(name = "id")
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;

    @Column(name = "firstname")
    @Expose
    private String firstname;

    private String middlename;

    @Column(name = "lastname")
    @Expose
    private String lastname;
    private String title;
    private String company;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Column(name = "home")
    @Type(type = "text")
    @Expose
    private String homephone;

    @Column(name = "mobile")
    @Type(type = "text")
    @Expose
    private String mobilephone;

    @Column(name = "work")
    @Type(type = "text")
    @Expose
    private String workphone;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String email2;

    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Transient
    private String group;

    @Transient
    private String allPhones;

    @Transient
    private String allMails;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    public File getPhoto() {
        if (photo == null) {
            return null;
        } else {
            return new File(photo);
        }
    }

    public String getAllMails() {
        return allMails;
    }

    public int getId() {
        return id;
    }

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

    public String getMobilephone() {
        return mobilephone;
    }

    public String getWorkphone() {
        return workphone;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(address, that.address) && Objects.equals(homephone, that.homephone) && Objects.equals(mobilephone, that.mobilephone) && Objects.equals(workphone, that.workphone) && Objects.equals(email, that.email) && Objects.equals(email2, that.email2) && Objects.equals(email3, that.email3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, address, homephone, mobilephone, workphone, email, email2, email3);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", home='" + homephone + '\'' +
                ", mobile='" + mobilephone + '\'' +
                ", work='" + workphone + '\'' +
                '}';
    }
}

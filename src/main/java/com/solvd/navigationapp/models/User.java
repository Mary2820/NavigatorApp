package com.solvd.navigationapp.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import com.solvd.navigationapp.enums.UserType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Client.class, Driver.class})
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Client.class),
    @JsonSubTypes.Type(value = Driver.class)
})
public abstract class User {
    @XmlElement(name = "id")
    protected Long id;

    @XmlElement(name = "firstName")
    protected String firstName;

    @XmlElement(name = "lastName")
    protected String lastName;

    @XmlElement(name = "userType")
    protected UserType userType;

    protected User(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserType getUserType()
    {
        return userType;
    }

}

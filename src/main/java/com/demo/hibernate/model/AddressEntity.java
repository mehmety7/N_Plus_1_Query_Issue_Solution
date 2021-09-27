package com.demo.hibernate.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS", uniqueConstraints = {@UniqueConstraint(columnNames = "ID")})
public class AddressEntity implements Serializable
{
    private static final long serialVersionUID = -1798070786993154676L;
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private Integer addressId;
    @Column(name = "ADDRESS", length = 500)
    private String address;
    @ManyToOne
    @JoinColumn(name="PERSON_ID")
    private PersonEntity person;

    public Integer getAddressId()
    {
        return addressId;
    }

    public void setAddressId(Integer addressId)
    {
        this.addressId = addressId;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }
}

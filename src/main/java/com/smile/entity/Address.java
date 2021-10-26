package com.smile.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="address", schema = "public")
public class Address implements Serializable {

    @Id
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq", allocationSize = 1)
    @GeneratedValue(generator = "address_seq", strategy = GenerationType.SEQUENCE)
    @Column(name="address_id")
    private Long addressId;

    @Column(name="number")
    private int number;

    @Column(name="street")
    private String street;

    @Column(name="zipcode")
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    public Address() {
    }

    public Address(Long addressId, int number, String street, String zipCode, Merchant merchant) {
        this.addressId = addressId;
        this.number = number;
        this.street = street;
        this.zipCode = zipCode;
        this.merchant = merchant;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
}

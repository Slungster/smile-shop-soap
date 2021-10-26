package com.smile.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="merchant", schema = "public")
public class Merchant implements Serializable {

    @Id
    @SequenceGenerator(name = "merchant_seq", sequenceName = "merchant_seq", allocationSize = 1)
    @GeneratedValue(generator = "merchant_seq", strategy = GenerationType.SEQUENCE)
    @Column(name="merchant_id")
    private Long merchantId;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="name")
    private String name;

    @Column(name="last_name")
    private String lastName;

    @Column(name="birth_date")
    private Date birthDate;

    @OneToMany(mappedBy = "merchant", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

/*    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "merchant_product",
            joinColumns = { @JoinColumn(name = "merchant_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    private Set<Product> products = new HashSet<>();*/

    @OneToMany(mappedBy = "merchant", cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<MerchantProduct> merchantProduct;

    public Merchant() {
    }

    public Merchant(Long merchantId, Date createDate, String name, String lastName, Date birthDate, List<Address> addresses/*, Set<Product> products*/, Set<MerchantProduct> merchantProduct) {
        this.merchantId = merchantId;
        this.createDate = createDate;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.addresses = addresses;
        //this.products = products;
        this.merchantProduct = merchantProduct;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

/*    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }*/

    public Set<MerchantProduct> getMerchantProduct() {
        return merchantProduct;
    }

    public void setMerchantProduct(Set<MerchantProduct> merchantProduct) {
        this.merchantProduct = merchantProduct;
    }
}

package com.smile.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="product", schema = "public")
public class Product implements Serializable {

    @Id
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    @GeneratedValue(generator = "product_seq", strategy = GenerationType.SEQUENCE)
    @Column(name="product_id")
    private Long productId;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="label")
    private String label;

    @Column(name="unit_price")
    private double unitPrice;

    @Column(name="currency")
    private String currency;

    @Column(name="weight")
    private double weight;

    @Column(name="height")
    private double height;

/*    @ManyToMany(mappedBy = "products")
    private Set<Merchant> merchants = new HashSet<>();*/

    @OneToMany(mappedBy = "product", cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<MerchantProduct> merchantProduct;

    public Product() {
    }

    public Product(Long productId, Date createDate, String label, double unitPrice, String currency, double weight, double height/*, Set<Merchant> merchants*/, Set<MerchantProduct> merchantProduct) {
        this.productId = productId;
        this.createDate = createDate;
        this.label = label;
        this.unitPrice = unitPrice;
        this.currency = currency;
        this.weight = weight;
        this.height = height;
        //this.merchants = merchants;
        this.merchantProduct = merchantProduct;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

/*    public Set<Merchant> getMerchants() {
        return merchants;
    }

    public void setMerchants(Set<Merchant> merchants) {
        this.merchants = merchants;
    }*/

    public Set<MerchantProduct> getMerchantProduct() {
        return merchantProduct;
    }

    public void setMerchantProduct(Set<MerchantProduct> merchantProduct) {
        this.merchantProduct = merchantProduct;
    }
}

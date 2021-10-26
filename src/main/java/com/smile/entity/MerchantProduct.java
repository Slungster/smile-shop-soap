package com.smile.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "merchant_product")
public class MerchantProduct {

    @Id
    @SequenceGenerator(name = "merchant_product_seq", sequenceName = "merchant_product_seq", allocationSize = 1)
    @GeneratedValue(generator = "merchant_product_seq", strategy = GenerationType.SEQUENCE)
    @Column(name="merchant_product_id")
    private Long merchantProductId;

    /*@EmbeddedId
    private MerchantProductId merchantProductId = new MerchantProductId();*/

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    /*@ManyToOne
    @MapsId("merchantId")*/
    private Merchant merchant;

    @ManyToOne
    @JoinColumn(name = "product_id")
    /*@ManyToOne
    @MapsId("productId")*/
    private Product product;

    @Column(name="association_date")
    private Date associationDate;

    public MerchantProduct() {
    }

    public MerchantProduct(Long merchantProductId, Merchant merchant, Product product, Date associationDate) {
        this.merchantProductId = merchantProductId;
        this.merchant = merchant;
        this.product = product;
        this.associationDate = associationDate;
    }

    public Long getMerchantProductId() {
        return merchantProductId;
    }

    public void setMerchantProductId(Long merchantProductId) {
        this.merchantProductId = merchantProductId;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getAssociationDate() {
        return associationDate;
    }

    public void setAssociationDate(Date associationDate) {
        this.associationDate = associationDate;
    }

    /*@Embeddable
    public static class MerchantProductId implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long merchantId;
        private Long productId;

        public MerchantProductId() {
        }

        public MerchantProductId(Long merchantId, Long productId) {
            this.merchantId = merchantId;
            this.productId = productId;
        }

        public Long getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(Long merchantId) {
            this.merchantId = merchantId;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }
    }*/
}

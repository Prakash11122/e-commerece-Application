package com.Spring.SpringBoot.entity;


import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name="Products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pr_id")
    private long Pid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id",referencedColumnName = "cat_id")
    private Category category;

    @Column(name="pr_img")
    private String Pimg;

    @Column(name="pr_name")
    private String Pname;

    @Column(name="pr_brand")
    private String Pbrand;

    @Column(name="pr_madein")
    private String Pmadein;

    @Column(name="pr_desc")
    private String Pdesc;

    @Column(name="pr_price")
    private long Pprice;

    public Products() {
    }
    @Transient
    public String getPhotosImagePath() {
        if (Pimg == null || Objects.isNull(Pid)) return null;

        return "/productImages/" + Pid +"/" + Pimg;
    }

    public long getPid() {
        return Pid;
    }

    public void setPid(long pid) {
        Pid = pid;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPimg() {
        return Pimg;
    }

    public void setPimg(String Pimg) {
        this.Pimg = Pimg;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public String getPbrand() {
        return Pbrand;
    }

    public void setPbrand(String pbrand) {
        Pbrand = pbrand;
    }

    public String getPmadein() {
        return Pmadein;
    }

    public void setPmadein(String pmadein) {
        Pmadein = pmadein;
    }

    public String getPdesc() {
        return Pdesc;
    }

    public void setPdesc(String pdesc) {
        Pdesc = pdesc;
    }

    public long getPprice() {
        return Pprice;
    }

    public void setPprice(long pprice) {
        Pprice = pprice;
    }



    public Products(long pid, Category category, String pimg,String pname, String pbrand, String pmadein, String pdesc, long pprice) {
        Pid = pid;
        this.category = category;
        Pimg = pimg;
        Pname = pname;
        Pbrand = pbrand;
        Pmadein = pmadein;
        Pdesc = pdesc;
        Pprice = pprice;
    }

    @Override
    public String toString() {
        return "Products{" +
                "Pid=" + Pid +
                ", category=" + category +
                ", Pimg='" + Pimg + '\'' +
                ", Pname='" + Pname + '\'' +
                ", Pbrand='" + Pbrand + '\'' +
                ", Pmadein='" + Pmadein + '\'' +
                ", Pdesc='" + Pdesc + '\'' +
                ", Pprice=" + Pprice +
                '}';
    }
}

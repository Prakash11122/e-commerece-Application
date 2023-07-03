package com.Spring.SpringBoot.entity;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cat_id")
    private long Cid;

    @Column(name="cat_name")
    private String Cname;

    public long getCid() {
        return Cid;
    }

    public void setCid(long Cid) {
        this.Cid = Cid;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String Cname) {
        this.Cname = Cname;
    }
}

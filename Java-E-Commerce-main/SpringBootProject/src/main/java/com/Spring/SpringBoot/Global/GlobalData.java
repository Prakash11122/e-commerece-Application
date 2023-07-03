package com.Spring.SpringBoot.Global;

import com.Spring.SpringBoot.entity.Products;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Products> cart;
    static{
        cart=new ArrayList<Products>();
    }
}

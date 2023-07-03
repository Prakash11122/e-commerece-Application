package com.Spring.SpringBoot.Controller;

import com.Spring.SpringBoot.Dao.ProductDao;
import com.Spring.SpringBoot.Global.GlobalData;
import com.Spring.SpringBoot.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingCartController {

    @Autowired
    private ProductDao productDao;

   @GetMapping("/addtocart")
   public String addGet(@RequestParam("id")long cid, Model model)
   {
       GlobalData.cart.add(productDao.findById(cid).get());
       return "redirect:/shop";
   }

    @GetMapping("/cart")
    public  String showCart (Model model)
    {
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Products::getPprice).sum());
        model.addAttribute("cart",GlobalData.cart);
        model.addAttribute("title","shopping cart");
        return "Shopping_cart";
    }

    @GetMapping("/removeItem")
    public String removeItem(@RequestParam("i") int index)
    {
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }
}

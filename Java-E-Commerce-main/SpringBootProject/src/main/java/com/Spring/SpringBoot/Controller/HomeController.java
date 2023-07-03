package com.Spring.SpringBoot.Controller;

import com.Spring.SpringBoot.Dao.CategoryDao;
import com.Spring.SpringBoot.Dao.ProductDao;
import com.Spring.SpringBoot.Dao.UserDao;
import com.Spring.SpringBoot.Global.GlobalData;
import com.Spring.SpringBoot.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    @GetMapping({"/","/dashboard"})
    public String dashboard(Model model)
    {
       /* Pageable pageable= PageRequest.of(0,10);
        Page<Products> page= productDao.findAll(pageable);
        List<Products> productsList=page.getContent();
        model.addObject("title","dashboard");

        model.addObject("products",productsList);*/
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("title", "index");
        return "dashboard";
    }
    @GetMapping("/shop")
    public String getAllCategories(Model model)
    {
        model.addAttribute("title", "View All Products");
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("categories",categoryDao.findAll());
        model.addAttribute("products",productDao.findAll());
        return "shop";
    }

    @GetMapping("/shopByCat")
    public String shopByCategory (@RequestParam("id")long Cid, Model model, Category category)
    {
        model.addAttribute("title", "ViewBy Category");
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("categories",categoryDao.findAll());
        model.addAttribute("products",productDao.findProductsByCategory(Cid));
        return "shop";
    }
    @GetMapping (value="/productview")
    public String viewProduct(@RequestParam("id")long pid,Model model)
    {
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("title", "Viewed Product");
        model.addAttribute("product",productDao.findById(pid).get());
        return "productView";
    }
    /*@GetMapping("/viewcart")
    public String viewCart(Model model)
    {
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("title", "My cart");
        return "Shopping_cart";
    }*/


}

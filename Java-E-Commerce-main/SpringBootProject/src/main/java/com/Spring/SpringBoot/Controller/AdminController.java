package com.Spring.SpringBoot.Controller;

import com.Spring.SpringBoot.Dao.CategoryDao;
import com.Spring.SpringBoot.Dao.ProductDao;
import com.Spring.SpringBoot.entity.Category;
import com.Spring.SpringBoot.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    //public static String uploadDir= ClassPathResource("/static/productImages");
   // public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/Pimages/";

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;


    @GetMapping(value="/adminPanel")
    public String adminDashboard(Model model)
    {
        model.addAttribute("title","Admin Dashboard");
        return "AdminPanel";
    }
    @GetMapping(value="/categories")
    public String getcategories(Model model)
    {
        Pageable pageable= PageRequest.of(0,10);
        Page<Category> page= categoryDao.findAll(pageable);
        List<Category> CategoryList=page.getContent();
        model.addAttribute("categories",CategoryList);
        model.addAttribute("title","List Categories");
        return "ViewCategories";
    }

    @GetMapping(value ="/addCategory")
    public String addCategories(Model model)
    {
        model.addAttribute("category", new Category());
        model.addAttribute("title","Add Categories");
        return "AddCategory";
    }

    @PostMapping(value="/addCategory")
    public String addCategoryPost(@ModelAttribute("category") Category category)
    {
        categoryDao.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/deleteCategory")
    public String deleteCat(@RequestParam("id") long Cid)
    {
        categoryDao.deleteById(Cid);
        return "redirect:/categories";
    }

    @GetMapping("/updateCategory")
    public String updateCat(@RequestParam("id") long Cid, Model model)
    {
        Optional<Category> category= categoryDao.findById(Cid);
        if(category.isPresent())
        {
            model.addAttribute("category", category.get());
            return "AddCategory";
        }
        else{
            return "404";
        }
    }

    //------------------Product API section------------------------------------------

    @GetMapping(value="/products")
    public String getProducts(Model model)
    {
        Pageable pageable= PageRequest.of(0,10);
        Page<Products> page= productDao.findAll(pageable);
        List<Products> ProductsList=page.getContent();
        model.addAttribute("products",ProductsList);
        model.addAttribute("title","List Products");
        return "ViewProducts";
    }

    @GetMapping(value ="/addProduct")
    public String addProduct(Model model)
    {
        model.addAttribute("product", new Products());
        model.addAttribute("categories",categoryDao.findAll());
        model.addAttribute("title","Add Products");
        return "AddProduct";
    }

    @PostMapping(value="/addProduct",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, "multipart/form-data;charset=UTF-8" })
    public String addProductPost(@ModelAttribute("product") Products products, BindingResult result,
                                 @RequestParam("imagefile") MultipartFile file, Model model) throws IOException {
        if(result.hasErrors())
        {
            return "AddProduct";
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        products.setPimg(fileName);

        Products savedProducts = productDao.save(products);

        String uploadDir = "./productImages/" + savedProducts.getPid();

        FileUploadUtil.saveFile(uploadDir, fileName, file);
        productDao.save(products);
        return "redirect:/products";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("id") long Pid)
    {
        productDao.deleteById(Pid);
        return "redirect:/products";
    }

    @GetMapping("/updateProduct")
    public String updateProduct(@RequestParam("id") long Pid, Model model)
    {
        Optional<Products> products= productDao.findById(Pid);
        if(products.isPresent())
        {
            model.addAttribute("product", products.get());
            return "AddProduct";
        }
        else{
            return "404";
        }
    }

}


















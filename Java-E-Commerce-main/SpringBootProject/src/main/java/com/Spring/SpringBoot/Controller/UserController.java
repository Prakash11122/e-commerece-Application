package com.Spring.SpringBoot.Controller;

import com.Spring.SpringBoot.Dao.ConfirmationTokenDao;
import com.Spring.SpringBoot.Dao.ProductDao;
import com.Spring.SpringBoot.Dao.UserDao;
import com.Spring.SpringBoot.entity.ConfirmationToken;
import com.Spring.SpringBoot.entity.User;
import com.Spring.SpringBoot.services.EmailSenderService;
import freemarker.template.TemplateException;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ConfirmationTokenDao confirmationTokenDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private EmailSenderService emailSenderService;
    private JavaMailSender javaMailSender;

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView displayRegistration(ModelAndView modelAndView, User user)
    {
        modelAndView.addObject("title","User-Signup");
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modelAndView, User user) throws MessagingException, TemplateException, IOException {

        User existingEmail = userDao.findByEmailIgnoreCase(user.getEmail());
        if(existingEmail != null)
        {
            modelAndView.addObject("message","Email already exists");
            modelAndView.setViewName("error");
        }
        else
        {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));
            userDao.save(user);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenDao.save(confirmationToken);
            emailSenderService.sendEmail(user,confirmationToken);
            modelAndView.addObject("email", user.getEmail());
            modelAndView.setViewName("successfulRegistration");
        }

        return modelAndView;
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenDao.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userDao.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setIsEnabled(true);
            userDao.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }

    @RequestMapping(value="/reset_password", method = RequestMethod.GET)
    public ModelAndView displayResetPasswordForm(ModelAndView modelAndView)
    {
        modelAndView.addObject("title","Reset Password");
        modelAndView.setViewName("reset_password");
        return modelAndView;
    }
    @RequestMapping(value="/reset_password", method = RequestMethod.POST)
    public ModelAndView ProcessResetPassword(ModelAndView modelAndView/*, HttpServletRequest request*/,User user,
                                             @RequestParam("email") String email)
            throws MessagingException, TemplateException, IOException {
        String token= RandomString.make(45);

        User Exemail = userDao.findByEmailIgnoreCase(email);
        if(Exemail != null)
        {
            Exemail.setResetPasswordToken(token);
            userDao.save(Exemail);
            String Rtoken=Exemail.getResetPasswordToken();
            emailSenderService.sendEmail(email,Rtoken);
            modelAndView.addObject("email", user.getEmail());
            modelAndView.setViewName("successfulRegistration");
        }
        else{
            modelAndView.addObject("message","can not find email");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }

   @RequestMapping(value="/confirm-reset", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmReset(ModelAndView modelAndView, @RequestParam("token")String resetToken)
    {
        User Rtoken=userDao.findByResetPasswordToken(resetToken);

        if(Rtoken != null)
        {
            modelAndView.addObject("token",Rtoken);
            modelAndView.setViewName("NewPassword");
            User user=userDao.findByEmailIgnoreCase(Rtoken.getEmail());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));
            user.setResetPasswordToken(null);
            userDao.save(user);
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }

    @GetMapping (value="/login")
    public String login(ModelAndView model)
    {
        //GlobalData.cart.clear();
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null || authentication instanceof AnonymousAuthenticationToken)
        {
            return "login";
        }
        model.addObject("title", "Log in page");
        return "dashboard";
    }

    @GetMapping(value="/logout")
    public String Logout(ModelAndView model, HttpServletRequest request, HttpServletResponse response)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null)
        {
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        model.addObject("title","logout");
        model.addObject("message","Oops!! Something wents wrong");
        return "redirect:/login?logout";
    }

}

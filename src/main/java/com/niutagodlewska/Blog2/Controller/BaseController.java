package com.niutagodlewska.Blog2.Controller;


import com.niutagodlewska.Blog2.Models.Article;
import com.niutagodlewska.Blog2.Models.UserDTO;
import com.niutagodlewska.Blog2.Repositories.UserRepo;
import com.niutagodlewska.Blog2.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.validation.Valid;
import java.text.ParseException;

@Controller
public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);
    private final UserService userService;

    @Autowired
    public BaseController(UserService userService){
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute UserDTO user, Model model){
        model.addAttribute("userDTO", user);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserDTO user, BindingResult bindingResult){

        //check if the user exists
        if(userService.userExists(user.getUsername())){
            bindingResult.addError(new FieldError("userDTO", "username", "Username is already in use"));
        }
        //check if the password contains digits
        if(user.getPassword() !=null ) {
            if(!user.getPassword().matches(".*[0-9].*")){
                bindingResult.addError(new FieldError("userDTO", "password","Your password must contain at least one digit"));
            }
            if(!user.getPassword().matches(".*[A-Z].*")){
                bindingResult.addError(new FieldError("userDTO", "password","Your password must contain at least one uppercase"));
            }
            if(!user.getPassword().matches(".*[a-z].*")){
                bindingResult.addError(new FieldError("userDTO", "password","Your password must contain at least one lowercase"));
            }
        }
        //check if the password matches
        if(user.getPassword() !=null && user.getRpassword() != null ){
            if(!user.getPassword().equals(user.getRpassword())){
                bindingResult.addError(new FieldError("userDTO", "rpassword", "Passwords don't match"));
            }
        }

        if(bindingResult.hasErrors()){
            return "/register";
        }
        user.setRole("USER");
        user.setActive(true);
        userRepo.save(user);
        log.info("saved: user: {}", user.toString());
        return "redirect:/login";
    }


}

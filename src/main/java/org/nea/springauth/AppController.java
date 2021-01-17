package org.nea.springauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository repo;

    @GetMapping("")
    public String viewHomePage() {

        return "index" ;
    }

    @GetMapping("/register")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User() );
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user) {
        BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
        String encodedPassword = pwdEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        repo.save(user);
        return "register_success";
    }

    @GetMapping("list_users")
    public String viewUsersList(Model model) {
        List<User> users = repo.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}

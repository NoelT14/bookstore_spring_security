package NoelSpring.Bookstore.Controller;

import NoelSpring.Bookstore.Entity.MyUser;
import NoelSpring.Bookstore.Repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class RegisterController {

     @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/register")
    public String getRegister(Model model, MyUser user){
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute MyUser user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
         myUserRepository.save(user);
         return "redirect:/login";

    }

}

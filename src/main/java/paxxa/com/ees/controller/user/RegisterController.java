package paxxa.com.ees.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paxxa.com.ees.entity.user.User;
import paxxa.com.ees.service.user.UserService;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;
    @ModelAttribute("user")
    public User constructUser() {
        return new User();
    }


    @RequestMapping("/sa/register")
    public String showRegister() {
        return "sa-adminRegister";
    }

    @RequestMapping(value = "/sa/register", method = RequestMethod.POST)
    public String doRegister(@Validated @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "sa-adminRegister";
        }
        userService.saveAdmin(user);
        return "redirect:/sa/register.html?success=true";
    }

    @RequestMapping("/register/available")
    @ResponseBody
    public String isAvailable(@RequestParam String username) {
        Boolean available = userService.findUserByUserName(username) == null;
        return available.toString();
    }

}

package paxxa.com.ees.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import paxxa.com.ees.entity.user.User;
import paxxa.com.ees.service.user.UserService;

@Controller
public class SuperAdminController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User construct(){
        return new User();
    }

    @RequestMapping("/sa/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "sa-users";
    }

    @RequestMapping("/sa/users/{id}")
    public String getUserDetails(Model model, @PathVariable int id) {
        model.addAttribute("user", userService.findById(id));
        return "sa-userDetails";
    }

    @RequestMapping("/sa/register")
    public String showRegister() {
        return "sa-adminRegister";
    }

    @RequestMapping(value="/sa/register", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("user") User user) {
        userService.saveAdmin(user);
        return "sa-adminRegister";
    }



}

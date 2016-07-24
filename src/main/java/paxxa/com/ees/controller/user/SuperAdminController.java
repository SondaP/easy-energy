package paxxa.com.ees.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import paxxa.com.ees.service.user.UserService;

@Controller
public class SuperAdminController {

    @Autowired
    private UserService userService;

    @RequestMapping("/sa/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "superAdmin-users";
    }

    @RequestMapping("/sa/users/{id}")
    public String getUserDetails(Model model, @PathVariable int id) {
        model.addAttribute("user", userService.findById(id));
        return "superAdmin-userDetails";
    }

    

}

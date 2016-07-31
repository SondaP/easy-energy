package paxxa.com.ees.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import paxxa.com.ees.entity.user.User;
import paxxa.com.ees.service.user.UserService;

@Controller
@RequestMapping("/sa")
public class SuperAdminController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "sa-users";
    }

    @RequestMapping("/users/{id}")
    public String getUserDetails(Model model, @PathVariable int id) {
        model.addAttribute("user", userService.findById(id));
        return "sa-userDetails";
    }

    @RequestMapping(value = "/users/remove/{id}")
    public String removeUser(@PathVariable int id) {
        userService.removeUser(id);
        return "redirect:/sa/users.html";
    }

    /*@RequestMapping(value = "/users/{id}/{pass}")
    public String resetPassword(@PathVariable int id, @PathVariable String pass) {
        User userEntity = userService.findById(id);
        userService.updatePassword(userEntity, pass);
        return "";
    }*/



}

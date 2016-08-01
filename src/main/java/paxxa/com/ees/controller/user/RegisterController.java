package paxxa.com.ees.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paxxa.com.ees.entity.user.User;
import paxxa.com.ees.service.user.UserService;
import paxxa.com.utils.DomainConstans;

import java.security.Principal;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User constructUser() {
        return new User();
    }


    @RequestMapping(value = {"/sa/register", "/a/register"})
    public String showRegister(Principal principal) {
        if (userService.hasUserExpectedRole(principal.getName(), DomainConstans.ROLE.ROLE_SUPER_ADMIN)) {
            return "sa-adminRegister";
        }
        if (userService.hasUserExpectedRole(principal.getName(), DomainConstans.ROLE.ROLE_ADMIN)) {
            return "a-userRegister";
        }
        return null;
    }

    @RequestMapping(value = {"/sa/register", "/a/register"}, method = RequestMethod.POST)
    public String doRegister(@Validated @ModelAttribute("user") User user, BindingResult result, Principal principal) {
        if (userService.hasUserExpectedRole(principal.getName(), DomainConstans.ROLE.ROLE_SUPER_ADMIN)) {
            if (result.hasErrors()) {
                return "sa-adminRegister";
            }
            userService.saveUserWithRoleAdmin(user);
            return "redirect:/sa/register.html?success=true";
        }
        if (userService.hasUserExpectedRole(principal.getName(), DomainConstans.ROLE.ROLE_ADMIN)) {
            userService.saveUserWithRoleTrader(user, principal.getName());
            return "redirect:/a/register.html?success=true";
        }
        return null;
    }

    @RequestMapping("/register/available")
    @ResponseBody
    public String isAvailable(@RequestParam String username) {
        Boolean available = userService.findUserByUserName(username) == null;
        return available.toString();
    }

}

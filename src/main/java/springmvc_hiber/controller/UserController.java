package springmvc_hiber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springmvc_hiber.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String test(Model model) {

        model.addAttribute("user", userService.listUsers().get(0));
        return "test";
    }
}

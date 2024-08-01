package springmvc_hiber.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springmvc_hiber.model.User;
import springmvc_hiber.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users";
    }

    @GetMapping("add-user")
    public String addUser(@ModelAttribute(name = "user") User user) {
        return "add-user";
    }

    @PostMapping("/save-new-user")
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.saveNewUser(user);
        return "redirect:/";
    }

    @GetMapping("/update-user")
    public String updateUser(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "update-user";
    }

    @PostMapping("/save-existing-user")
    public String saveExistingUser(@ModelAttribute("user") User user) {
        userService.saveExistingUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete-user")
    public String deleteUser(@RequestParam(name = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}

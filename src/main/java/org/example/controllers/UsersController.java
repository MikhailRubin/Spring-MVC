package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.exception.EntityNotFoundException;
import org.example.models.User;
import org.example.services.UsersServiceImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final UsersServiceImpl usersService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", usersService.findAll());
        return "users/index";
    }

    @GetMapping("/address")
    public String getAddress(Model model, @RequestParam int id) throws EntityNotFoundException {
        User address = usersService.findOne(id);
        model.addAttribute("address", address);
        return "users/address";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        usersService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable("id") int id) throws EntityNotFoundException {
        model.addAttribute("user", usersService.findOne(id));
        return "users/new";
    }

    @GetMapping(value = "/new")
    public String userCreate(Model model, User user) {
        model.addAttribute("user", user);
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user")  User user){
        usersService.save(user);
        return "redirect:/users";
    }
}
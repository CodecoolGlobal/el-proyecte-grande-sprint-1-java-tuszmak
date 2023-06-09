package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.controller.dto.user.NewUser;
import com.codecool.ecosampler.controller.dto.user.UserDTO;
import com.codecool.ecosampler.controller.dto.user.UserForSelectDTO;
import com.codecool.ecosampler.domain.Role;
import com.codecool.ecosampler.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;

    @GetMapping("/{publicId}")
    public UserDTO getUserByID(@PathVariable UUID publicId) {
        return userService.getUserDTOByPublicId(publicId);
    }

    @GetMapping("/by-role")
    public List<UserForSelectDTO> getUsersForSelectDTOByRole(@RequestParam Role role) {
        return userService.getUsersForSelectDTOByRole(role);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO registerUser(@RequestBody NewUser newUser){
       return userService.registerUser(newUser);
    }

    @DeleteMapping("/{publicId}")
    public void deleteUser(@PathVariable UUID publicId){
        userService.deleteUserByPublicId(publicId);
    }
}

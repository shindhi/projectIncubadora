package com.invillia.projectSpring.controller;

import com.invillia.projectSpring.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeamController {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("team", teamRepository.findAll());
        return "index";
    }

}

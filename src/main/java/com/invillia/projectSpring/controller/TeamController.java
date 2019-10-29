package com.invillia.projectSpring.controller;

import com.invillia.projectSpring.domain.Team;
import com.invillia.projectSpring.exceptions.ActionNotPermitedException;
import com.invillia.projectSpring.exceptions.TeamNotFoundException;
import com.invillia.projectSpring.repository.TeamRepository;
import com.invillia.projectSpring.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("team", teamService.findAll());
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpform(Team team){
        return "add-team";
    }

    @PostMapping("/addteam")
    public String addTeam(@Valid Team team, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-team";
        }
        teamService.insert(team);
        model.addAttribute("teams", teamService.findAll());
        return "index";
    }
    @GetMapping("/edit{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        Team team = teamService.findById(id);
        model.addAttribute("team", team);
        return "update-team";
    }

    @PostMapping("/update/{id}")
    public String updateTeam(@PathVariable("id") Long id, @Valid Team team, BindingResult result, Model model){
        if(result.hasErrors()){
            team.setId(id);
            return "update-team";
        }
        teamService.update(id, team);
        model.addAttribute("teams", teamService.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeam(@PathVariable("id") Long id, Model model){
        teamService.findById(id);
        teamService.deleteById(id);
        model.addAttribute("teams", teamService.findAll());
        return "index";
    }

    @ExceptionHandler(ActionNotPermitedException.class)
    public void exceptionHandler(HttpServletResponse response, Exception e) throws IOException{
        response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage());
    }
}

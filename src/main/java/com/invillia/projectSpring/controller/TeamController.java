package com.invillia.projectSpring.controller;

import com.invillia.projectSpring.domain.Team;
import com.invillia.projectSpring.exceptions.ActionNotPermitedException;
import com.invillia.projectSpring.service.MemberServices;
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
    private final MemberServices memberServices;

    @Autowired
    public TeamController(TeamService teamService, MemberServices memberServices) {
        this.teamService = teamService;
        this.memberServices = memberServices;
    }


    @GetMapping("/team")
    public String index(Model model) {
        model.addAttribute("teams", teamService.findAll());
        return "Team/index";
    }

    @GetMapping("/signupteam")
    public String showSignUpform(Team team) {
        return "Team/RegisterTeam";
    }

    @PostMapping("/addteam")
    public String addMember(@Valid Team team, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Team/RegisterTeam";
        }
        teamService.insert(team);
        model.addAttribute("teams", teamService.findAll());
        return "index";
    }

    @GetMapping("/editteam/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Team team = teamService.findById(id);
        model.addAttribute("team", team);
        return "Team/UpdateTeam";
    }

    @PostMapping("/updateteam/{id}")
    public String updateTeam(@PathVariable Long id, @Valid Team team, BindingResult result, Model model) {
        if (result.hasErrors()) {
            team.setId(id);
            return "Team/UpdateTeam";
        }
        teamService.update(team);
        model.addAttribute("teams", teamService.findAll());
        return "index";
    }

    @GetMapping("/deleteteam/{id}")
    public String deleteTeam(@PathVariable Long id, Model model) {
        teamService.findById(id);
        teamService.deleteById(id);
        model.addAttribute("teams", teamService.findAll());
        return "index";
    }


    @GetMapping("/listmembers/{id}")
    public String listMembers(@PathVariable Long id, Model model) {

        model.addAttribute("members", memberServices.findAllMembersByIdTeam(id));
        model.addAttribute("teams", teamService.findById(id));

        return "Team/ListMember";
    }


    @ExceptionHandler(ActionNotPermitedException.class)
    public void exceptionHandler(HttpServletResponse response, Exception e) throws IOException {
        response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage());
    }
}

package com.invillia.projectSpring.controller;

import com.invillia.projectSpring.domain.Member;
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
import java.time.LocalDateTime;

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

        return "redirect:/team";
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
        return "redirect:/team";
    }

    @GetMapping("/deleteteam/{id}")
    public String deleteTeam(@PathVariable Long id, Model model) {
        teamService.findById(id);
        teamService.deleteById(id);
        return "redirect:/team";
    }

    @GetMapping("/listmembers/{id}")
    public String listMembers(@PathVariable Long id, Model model) {

        model.addAttribute("members", memberServices.findAllMembersByIdTeam(id));
        model.addAttribute("teams", teamService.findById(id));

        return "Team/ListMember";
    }
    @GetMapping("/signupmemberteam/{id}")
    public String showSignUpformMemberTeam(@PathVariable ("id") Long id, Model model, Member member) {

        model.addAttribute("teams", teamService.findById(id));

        return "Team/InsertMemberTeam";
    }

    @PostMapping("/addmemberteam")
    public String addMemberTeam(@Valid Member member, Model model, BindingResult result) {
        if(result.hasErrors()){
            return "Team/InsertMemberTeam";
        }
        memberServices.insert(member);
        return "redirect:/team";
    }

    @ExceptionHandler(ActionNotPermitedException.class)
    public void exceptionHandler(HttpServletResponse response, Exception e) throws IOException {
        response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage());
    }
}

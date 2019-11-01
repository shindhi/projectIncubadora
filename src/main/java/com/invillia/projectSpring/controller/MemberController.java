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
import javax.swing.*;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class MemberController {
    private final MemberServices memberServices;
    private final TeamService teamService;

    @Autowired
    public MemberController(MemberServices memberServices, TeamService teamService) {
        this.memberServices = memberServices;
        this.teamService = teamService;
    }

    @GetMapping("/member")
    public String index(Model model){
        model.addAttribute("members", memberServices.findAll());
        return "Member/index";
    }

    @GetMapping("/signupmember")
    public String showSignUpform(Member member, Model model){
        model.addAttribute("teams", teamService.findAll());
        return "Member/RegisterMember";
    }

    @PostMapping("/addmember")
    public String addMember(@Valid Member member, BindingResult result, Model model){
        if(result.hasErrors()){
            return "Member/RegisterMember";
        }

        memberServices.insert(member);

        return "redirect:/member";
    }
    @GetMapping("/editmember/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        Member member = memberServices.findById(id);
        model.addAttribute("member", member);
        return "Member/UpdateMember";
    }

    @PostMapping("/updatemember/{id}")
    public String updateMember(@PathVariable("id") Long id, @Valid Member member, BindingResult result, Model model){
        if(result.hasErrors()){
            member.setId(id);
            return "Member/UpdateMember";
        }
        memberServices.update(member);

        return "redirect:/member";
    }

    @GetMapping("/deletemember/{id}")
    public String deleteMember(@PathVariable("id") Long id, Model model){
        memberServices.findById(id);
        memberServices.deleteById(id);

        return "redirect:/member";
    }


    @ExceptionHandler(ActionNotPermitedException.class)
    public void exceptionHandler(HttpServletResponse response, Exception e) throws IOException {
        response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage());
    }
}

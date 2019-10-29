package com.invillia.projectSpring.controller;

import com.invillia.projectSpring.domain.Member;
import com.invillia.projectSpring.exceptions.ActionNotPermitedException;
import com.invillia.projectSpring.repository.MemberRepository;
import com.invillia.projectSpring.service.MemberServices;
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
public class MemberController {
    private final MemberServices memberServices;

    @Autowired
    public MemberController(MemberServices memberServices) {
        this.memberServices = memberServices;
    }

    @GetMapping("/member")
    public String index(Model model){
        model.addAttribute("members", memberServices.findAll());
        return "index";
    }

    @GetMapping("/signupmember")
    public String showSignUpform(Member member){
        return "add-member";
    }

    @PostMapping("/addmember")
    public String addMember(@Valid Member member, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-member";
        }
        memberServices.insert(member);
        model.addAttribute("members", memberServices.findAll());
        return "index";
    }
    @GetMapping("/editmember{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        Member member = memberServices.findById(id);
        model.addAttribute("member", member);
        return "update-team";
    }

    @PostMapping("/updatemember/{id}")
    public String updateMember(@PathVariable("id") Long id, @Valid Member member, BindingResult result, Model model){
        if(result.hasErrors()){
            member.setId(id);
            return "update-team";
        }
        memberServices.update(id, member);
        model.addAttribute("teams", memberServices.findAll());
        return "index";
    }

    @GetMapping("/deletemember/{id}")
    public String deleteMember(@PathVariable("id") Long id, Model model){
        memberServices.findById(id);
        memberServices.deleteById(id);
        model.addAttribute("teams", memberServices.findAll());
        return "index";
    }

    @ExceptionHandler(ActionNotPermitedException.class)
    public void exceptionHandler(HttpServletResponse response, Exception e) throws IOException {
        response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage());
    }
}

package com.invillia.projectSpring;

import com.invillia.projectSpring.domain.Member;
import com.invillia.projectSpring.domain.Team;
import com.invillia.projectSpring.service.MemberServices;
import com.invillia.projectSpring.service.TeamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RunnerProject implements CommandLineRunner {

    private final TeamService teamService;
    private final MemberServices memberServices;

    public RunnerProject(TeamService teamService, MemberServices memberServices) {
        this.teamService = teamService;
        this.memberServices = memberServices;
    }

    @Override
    public void run(String... args) throws Exception {
//        final Team team = new Team("Team Koleia");
//        teamService.insert(team);
//
//        final Member member = new Member("Teste", team);
//        memberServices.insert(member);
    }
}

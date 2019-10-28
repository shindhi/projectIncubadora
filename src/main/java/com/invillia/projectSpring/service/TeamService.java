package com.invillia.projectSpring.service;

import com.invillia.projectSpring.domain.Team;
import com.invillia.projectSpring.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TeamService {
    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public void insert(Team team) {
        teamRepository.save(team);
    }
}

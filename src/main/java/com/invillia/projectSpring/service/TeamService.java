package com.invillia.projectSpring.service;

import com.invillia.projectSpring.domain.Team;
import com.invillia.projectSpring.exceptions.ActionNotPermitedException;
import com.invillia.projectSpring.exceptions.TeamNotFoundException;
import com.invillia.projectSpring.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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

    @Transactional
    public void update(Long id, Team team){
        teamRepository.findById(id);
        teamRepository.save(team);

    }

    public void deleteById(Long id){
        teamRepository.findById(id).orElseThrow(()-> new TeamNotFoundException(String.valueOf(id)));
        teamRepository.deleteById(id);
    }

    public List<Team> findAll(){
       return teamRepository.findAll();
    }

    public Team findById(Long id){
        return teamRepository.findById(id).orElseThrow(() -> new ActionNotPermitedException(String.valueOf(id)));

    }
}

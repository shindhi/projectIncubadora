package com.invillia.projectSpring.service;

import com.invillia.projectSpring.domain.Member;
import com.invillia.projectSpring.domain.Team;
import com.invillia.projectSpring.exceptions.ActionNotPermitedException;
import com.invillia.projectSpring.exceptions.NotFoundException;
import com.invillia.projectSpring.repository.TeamRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
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
    public void update(Team team){
        Team persisted = teamRepository.findById(team.getId())
                .orElseThrow(IllegalArgumentException::new);
        persisted.setName(team.getName());
        teamRepository.save(persisted);

    }

    @Transactional
    public void deleteById(Long id){
        teamRepository.findById(id).orElseThrow(()-> new NotFoundException(String.valueOf(id)));
        teamRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Team> findAll(){
       return teamRepository.findAll(Sort.by("id"));
    }

    @Transactional(readOnly = true)
    public Team findById(Long id){
        return teamRepository.findById(id).orElseThrow(() -> new ActionNotPermitedException(String.valueOf(id)));
    }

}

package com.invillia.projectSpring.repository;

import com.invillia.projectSpring.domain.Member;
import com.invillia.projectSpring.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}

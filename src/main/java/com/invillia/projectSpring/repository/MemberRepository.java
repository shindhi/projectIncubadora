package com.invillia.projectSpring.repository;

import com.invillia.projectSpring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
    @Query(
            "SELECT m FROM Member m JOIN FETCH m.team t where t.id = :id_team"
    )
    List<Member> findAllMembersByIdTeam(@Param("id_team") Long id);

}

package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {


}

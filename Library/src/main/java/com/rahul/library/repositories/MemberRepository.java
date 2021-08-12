package com.rahul.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahul.library.entities.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}

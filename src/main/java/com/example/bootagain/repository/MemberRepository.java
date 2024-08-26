package com.example.bootagain.repository;

import com.example.bootagain.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {

}

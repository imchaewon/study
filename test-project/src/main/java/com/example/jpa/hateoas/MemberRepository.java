package com.example.jpa.hateoas;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;

@Transactional
//@RepositoryRestResource(collectionResourceRel = "account", path = "account")
@RepositoryRestResource
public interface MemberRepository extends JpaRepository<Member2, Long> {

	Member2 findByName123(@Param("ㅋㅋㅋㅋㅋ") String ㅌㅌㅌㅌㅌ);
}

package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface LockTestRepository extends CrudRepository<Member, String> {
	// 사용자 이름으로 멤버 조회 (락 걸기)
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Member> findByName(String username);

	// 사용자 이름으로 멤버 조회 (락 없음)
	Optional<Member> findById(String username);

	// 사용자 나이 업데이트
	@Modifying
	@Query("UPDATE Member m SET m.age = :newAge WHERE m.name = :name")
	void updateMember(@Param("name") String name, @Param("newAge") int newAge);
}

package study.querydsl.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.dto.QMemberTeamDto;
import study.querydsl.entity.Member;

import java.util.List;
import java.util.Optional;

import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {
	private final EntityManager em;
	private final JPAQueryFactory queryFactory;

	public void save(Member member) {
		em.persist(member);
	}

	public Optional<Member> findById(Long id) {
		Member findMember = em.find(Member.class, id);
		return Optional.ofNullable(findMember);
	}

	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}

	public List<Member> findAll_Querydsl() {
		return queryFactory
				.selectFrom(member)
				.fetch();
	}

	public List<Member> findByUsername(String username) {
		return em.createQuery("select m from Member m where m.username = :username", Member.class)
				.setParameter("username", username)
				.getResultList();
	}

	public List<Member> findByUsername_Querydsl(String username) {
		return queryFactory
				.selectFrom(member)
				.where(member.username.eq(username))
				.fetch();
	}

	public List<MemberTeamDto> searchByBuilder(MemberSearchCondition condition) {
		BooleanBuilder builder = new BooleanBuilder();

		if (StringUtils.hasText(condition.getUserName()))
			builder.and(member.username.eq(condition.getUserName()));

		if (StringUtils.hasText(condition.getTeamName()))
			builder.and(member.team.name.eq(condition.getTeamName()));

		if (condition.getAgeLoe() != null)
			builder.and(member.age.loe(condition.getAgeLoe()));

		if (condition.getAgeLoe() != null)
			builder.and(member.age.goe(condition.getAgeGoe()));

		return queryFactory
				.select(new QMemberTeamDto(
						member.id.as("memberId"),
						member.username,
						member.age,
						team.id.as("teamId"),
						team.name.as("teamName")
				))
				.from(member)
				.leftJoin(member.team, team)
				.where(builder)
				.fetch();
	}

	public List<MemberTeamDto> search(MemberSearchCondition condition) {
		return queryFactory
				.select(new QMemberTeamDto(
						member.id.as("memberId"),
						member.username,
						member.age,
						team.id.as("teamId"),
						team.name.as("teamName")
				))
				.from(member)
				.leftJoin(member.team, team)
				.where(
						usernameEq(condition.getUserName()),
						teamnameEq(condition.getTeamName()),
						ageLoe(condition.getAgeLoe()),
						ageGoe(condition.getAgeGoe())
				)
				.fetch();
	}

	private BooleanExpression usernameEq(String username) {
		return StringUtils.hasText(username) ? member.username.eq(username) : null;
	}

	private BooleanExpression teamnameEq(String teamname) {
		return StringUtils.hasText(teamname) ? member.team.name.eq(teamname) : null;
	}

	private BooleanExpression ageLoe(Integer ageLoe) {
		return ageLoe != null ? member.age.loe(ageLoe) : null;
	}

	private BooleanExpression ageGoe(Integer ageGoe) {
		return ageGoe != null ? member.age.goe(ageGoe) : null;
	}


//	private Predicate isCondition() {
//
//	}
}










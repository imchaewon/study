package study.querydsl.repository;

import com.querydsl.core.Query;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.dto.QMemberTeamDto;
import study.querydsl.entity.Member;

import java.util.List;

import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;

//public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {
public class MemberRepositoryImpl implements MemberRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	public MemberRepositoryImpl(EntityManager em) {
		queryFactory = new JPAQueryFactory(em);
	}

//	public MemberRepositoryImpl() {
//		super(Member.class);
//	}

	@Override
	public List<MemberTeamDto> search(MemberSearchCondition condition) {
//		EntityManager em = getEntityManager();

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
						usernameCond(condition.getUserName()),
						teamnameCond(condition.getTeamName()),
						ageLoeCond(condition.getAgeLoe()),
						ageGoeCond(condition.getAgeGoe())
				)
				.fetch();

//		return from(member)
//				.leftJoin(member.team, team)
//				.where(
//						usernameCond(condition.getUserName()),
//						teamnameCond(condition.getTeamName()),
//						ageLoeCond(condition.getAgeLoe()),
//						ageGoeCond(condition.getAgeGoe())
//				)
//				.select(new QMemberTeamDto(
//						member.id.as("memberId"),
//						member.username,
//						member.age,
//						team.id.as("teamId"),
//						team.name.as("teamName")
//				))
//				.fetch();
	}

	@Override
	public Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable) {
		QueryResults<MemberTeamDto> results = queryFactory
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
						usernameCond(condition.getUserName()),
						teamnameCond(condition.getTeamName()),
						ageLoeCond(condition.getAgeLoe()),
						ageGoeCond(condition.getAgeGoe())
				)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetchResults();

		List<MemberTeamDto> content = results.getResults();
		long total = results.getTotal();

		return new PageImpl<>(content, pageable, total);
//		QueryResults<MemberTeamDto> results = queryFactory
//
//		JPQLQuery<MemberTeamDto> jpaQuery = from(member)
//				.leftJoin(member.team, team)
//				.where(
//						usernameCond(condition.getUserName()),
//						teamnameCond(condition.getTeamName()),
//						ageLoeCond(condition.getAgeLoe()),
//						ageGoeCond(condition.getAgeGoe())
//				)
//				.select(new QMemberTeamDto(
//						member.id.as("memberId"),
//						member.username,
//						member.age,
//						team.id.as("teamId"),
//						team.name.as("teamName")
//				));
//
//		JPQLQuery<MemberTeamDto> result = getQuerydsl().applyPagination(pageable, jpaQuery);
//
//		List<MemberTeamDto> content = result.fetchResults().getResults();
//
//		long total = result.fetchResults().getTotal();
//
//		return new PageImpl<>(content, pageable, total);
	}

	@Override
	public Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable) {
		List<MemberTeamDto> content = queryFactory
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
						usernameCond(condition.getUserName()),
						teamnameCond(condition.getTeamName()),
						ageLoeCond(condition.getAgeLoe()),
						ageGoeCond(condition.getAgeGoe())
				)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

		JPAQuery<Member> countQuery = queryFactory
				.select(member)
				.from(member)
				.leftJoin(member.team, team)
				.where(
						usernameCond(condition.getUserName()),
						teamnameCond(condition.getTeamName()),
						ageLoeCond(condition.getAgeLoe()),
						ageGoeCond(condition.getAgeGoe())
				);

		return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchCount());
	}

	private BooleanExpression usernameCond(String username) {
		return StringUtils.hasText(username) ? member.username.eq(username) : null;
	}

	private BooleanExpression teamnameCond(String teamname) {
		return StringUtils.hasText(teamname) ? member.team.name.eq(teamname) : null;
	}

	private BooleanExpression ageLoeCond(Integer ageLoe) {
		return ageLoe != null ? member.age.loe(ageLoe) : null;
	}

	private BooleanExpression ageGoeCond(Integer ageGoe) {
		return ageGoe != null ? member.age.goe(ageGoe) : null;
	}
}

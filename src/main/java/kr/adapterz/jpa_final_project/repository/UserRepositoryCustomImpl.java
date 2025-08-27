package kr.adapterz.jpa_final_project.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.adapterz.jpa_final_project.entity.User;
import lombok.RequiredArgsConstructor;
import static kr.adapterz.jpa_final_project.entity.QUser.user;

import java.util.List;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<User> search(String email, String nickname) {
        return queryFactory
                .selectFrom(user)
                .where(
                        emailEq(email),
                        nicknameContains(nickname)
                )
                .orderBy(user.id.asc())
                .fetch();
    }

    private BooleanExpression emailEq(String email) {
        return (email == null || email.isBlank()) ? null
                : user.email.eq(email);
    }

    private BooleanExpression nicknameContains(String nickname) {
        return (nickname == null || nickname.isBlank()) ? null
                : user.nickname.containsIgnoreCase(nickname);
    }
}
package kr.adapterz.jpa_final_project.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.adapterz.jpa_final_project.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static kr.adapterz.jpa_final_project.entity.QPost.post;
import static kr.adapterz.jpa_final_project.entity.QUser.user;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Post> search(String title, String content, String authorNickname, Pageable pageable) {
        // content 조회
        List<Post> contentRows = queryFactory
                .selectFrom(post)
                .join(post.author, user).fetchJoin()
                .where(
                        titleContains(title),
                        contentContains(content),
                        authorNicknameEq(authorNickname)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.id.desc())
                .fetch();

        // count 쿼리
        JPAQuery<Long> countQuery = queryFactory
                .select(post.count())
                .from(post)
                .join(post.author, user)
                .where(
                        titleContains(title),
                        contentContains(content),
                        authorNicknameEq(authorNickname)
                );

        return PageableExecutionUtils.getPage(contentRows, pageable, countQuery::fetchOne);
    }

    private BooleanExpression titleContains(String v) {
        return (v == null || v.isBlank()) ? null : post.title.containsIgnoreCase(v);
    }
    private BooleanExpression contentContains(String v) {
        return (v == null || v.isBlank()) ? null : post.content.containsIgnoreCase(v);
    }
    private BooleanExpression authorNicknameEq(String v) {
        return (v == null || v.isBlank()) ? null : user.nickname.eq(v);
    }
}

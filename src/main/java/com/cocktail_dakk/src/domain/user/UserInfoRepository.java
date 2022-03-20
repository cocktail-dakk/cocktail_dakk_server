package com.cocktail_dakk.src.domain.user;

import com.cocktail_dakk.src.domain.user.projection.UserInfoStatusProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByEmail(String email);

    Optional<UserInfoStatusProjection> findStatusByEmail(String email);
}

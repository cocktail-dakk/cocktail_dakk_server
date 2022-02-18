package com.cocktail_dakk.src.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

//    Optional<UserInfo> findByDeviceNum(String deviceNum);

    Optional<UserInfo> findByEmail(String email);
}

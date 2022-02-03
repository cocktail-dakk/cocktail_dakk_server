package com.cocktail_dakk.src.domain.user;

import com.cocktail_dakk.src.domain.user.dto.*;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByDeviceNum(String deviceNum);
}

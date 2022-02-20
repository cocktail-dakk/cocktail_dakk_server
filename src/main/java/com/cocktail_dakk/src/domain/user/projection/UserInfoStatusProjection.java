package com.cocktail_dakk.src.domain.user.projection;

import com.cocktail_dakk.src.domain.Status;

public interface UserInfoStatusProjection {
    String getEmail();
    Status getStatus();
}

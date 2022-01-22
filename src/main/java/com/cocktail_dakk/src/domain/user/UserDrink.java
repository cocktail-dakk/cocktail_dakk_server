package com.cocktail_dakk.src.domain.user;

import com.cocktail_dakk.src.domain.drink.Drink;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class UserDrink {

    public UserDrink(UserInfo userInfo, Drink drink) {
        this.userInfo = userInfo;
        this.drink = drink;
        userInfo.getUserDrinks().add(this);
    }

    @Id
    @GeneratedValue
    private Long userDrinkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInfoId")
    UserInfo userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drinkId")
    Drink drink;
}

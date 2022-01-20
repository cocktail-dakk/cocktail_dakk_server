package java.com.cocktail_dakk.src.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserInfo {

    @Id @GeneratedValue
    private Long cocktailInfoId;

}

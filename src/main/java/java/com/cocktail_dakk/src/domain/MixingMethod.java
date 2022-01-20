package java.com.cocktail_dakk.src.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MixingMethod {

    @Id @GeneratedValue
    private Long mixingMethodId;
}

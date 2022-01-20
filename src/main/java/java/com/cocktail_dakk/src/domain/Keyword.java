package java.com.cocktail_dakk.src.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Keyword {

    @Id @GeneratedValue
    private Long keywordId;
    private String keywordName;
}

package java.com.cocktail_dakk.src.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class CocktailInfo {

    @Id @GeneratedValue
    private Long cocktailInfoId;
    private String englishName;
    private String koreanName;
    @Lob
    private String description;
    @Lob
    private String cocktailImageURL;
    @Lob
    private String cocktailBackgroundImageURL;
    @Lob
    private String recommendImageURL;
    private String status;
}

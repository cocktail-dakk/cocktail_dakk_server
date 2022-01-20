package java.com.cocktail_dakk.src.domain.cocktail;

import lombok.Getter;

import javax.persistence.*;
import java.com.cocktail_dakk.src.domain.keyword.Keyword;

@Entity
@Getter
public class CocktailKeyword {

    @Id @GeneratedValue
    private Long cocktailKeywordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keywordId")
    private Keyword keyword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cocktialInfoId")
    private CocktailInfo cocktailInfo;

}

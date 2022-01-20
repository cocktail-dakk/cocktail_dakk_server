package java.com.cocktail_dakk.src.domain.keyword;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.com.cocktail_dakk.src.domain.cocktail.CocktailKeyword;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Keyword {


    @Id @GeneratedValue
    private Long keywordId;

    private String keywordName;

    @OneToMany
    List<CocktailKeyword> cocktailKeywords=new ArrayList<>();
}

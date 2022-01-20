package java.com.cocktail_dakk.src.domain.keyword;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.com.cocktail_dakk.src.domain.cocktail.CocktailKeyword;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Keyword {

    @Id @GeneratedValue
    private Long keywordId;

    private String keywordName;

    @OneToMany
    List<CocktailKeyword> cocktailKeywords=new ArrayList<>();
}

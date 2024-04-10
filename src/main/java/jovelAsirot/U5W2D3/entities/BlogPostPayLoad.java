package jovelAsirot.U5W2D3.entities;

import jovelAsirot.U5W2D3.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class BlogPostPayLoad {
    private Category category;
    private String cover;
    private String content;
    private int readingTime;
    private Set<Long> authorIds;
}

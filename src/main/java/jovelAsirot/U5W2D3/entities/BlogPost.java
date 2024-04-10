package jovelAsirot.U5W2D3.entities;

import jakarta.persistence.*;
import jovelAsirot.U5W2D3.enums.Category;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Category category;

    private String cover;

    private String content;

    private int readingTime;

    @ManyToMany(mappedBy = "blogPosts")
    private List<BlogAuthor> authors = new ArrayList<>();
}

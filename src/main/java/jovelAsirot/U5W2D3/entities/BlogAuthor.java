package jovelAsirot.U5W2D3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class BlogAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String surname;

    private String email;

    private LocalDate birthDate;

    private String avatar;

    @ManyToMany
    private Set<BlogPost> blogPosts = new HashSet<>();
}

package jovelAsirot.U5W2D3.repositories;

import jovelAsirot.U5W2D3.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostDAO extends JpaRepository<BlogPost, Long> {
}

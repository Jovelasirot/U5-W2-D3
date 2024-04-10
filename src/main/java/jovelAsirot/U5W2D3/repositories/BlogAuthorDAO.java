package jovelAsirot.U5W2D3.repositories;

import jovelAsirot.U5W2D3.entities.BlogAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogAuthorDAO extends JpaRepository<BlogAuthor, Long> {
    
}

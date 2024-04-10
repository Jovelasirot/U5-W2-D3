package jovelAsirot.U5W2D3.services;

import jovelAsirot.U5W2D3.entities.BlogPost;
import jovelAsirot.U5W2D3.enums.Category;
import jovelAsirot.U5W2D3.exceptions.NotFoundException;
import jovelAsirot.U5W2D3.repositories.BlogPostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostDAO bpDAO;

    public Page<BlogPost> getAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.bpDAO.findAll(pageable);
    }

    public BlogPost save(BlogPost newBlogPost) {
        Random rdm = new Random();
        Category[] categories = Category.values();

        newBlogPost.setCategory(categories[rdm.nextInt(categories.length)]);
        return this.bpDAO.save(newBlogPost);
    }

    public BlogPost findById(Long blogId) {
        return this.bpDAO.findById(blogId).orElseThrow(() -> new NotFoundException(blogId));
    }

    public BlogPost updateById(Long blogId, BlogPost updatedBlogPost) {
        BlogPost blogPostFound = this.findById(blogId);

        blogPostFound.setAuthors(updatedBlogPost.getAuthors());
        blogPostFound.setCategory(updatedBlogPost.getCategory());
        blogPostFound.setContent(updatedBlogPost.getContent());

        return this.bpDAO.save(blogPostFound);
    }

    public void deleteById(Long blogId) {
        BlogPost blogPostFound = this.findById(blogId);
        this.bpDAO.delete(blogPostFound);
    }
}

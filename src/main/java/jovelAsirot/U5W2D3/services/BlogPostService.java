package jovelAsirot.U5W2D3.services;

import jovelAsirot.U5W2D3.entities.BlogAuthor;
import jovelAsirot.U5W2D3.entities.BlogPost;
import jovelAsirot.U5W2D3.entities.BlogPostPayLoad;
import jovelAsirot.U5W2D3.enums.Category;
import jovelAsirot.U5W2D3.exceptions.NotFoundException;
import jovelAsirot.U5W2D3.repositories.BlogAuthorDAO;
import jovelAsirot.U5W2D3.repositories.BlogPostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostDAO bpDAO;

    @Autowired
    private BlogAuthorDAO baDAO;

    public Page<BlogPost> getAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return this.bpDAO.findAll(pageable);
    }

    private Set<BlogAuthor> fetchAuthors(int numOfAuthors) {
        Set<BlogAuthor> authors = new HashSet<>();
        List<BlogAuthor> authorList = baDAO.findAll();

        Collections.shuffle(authorList);

        List<BlogAuthor> randomAuthors = authorList.subList(0, Math.min(numOfAuthors, authorList.size()));

        for (BlogAuthor author : randomAuthors) {
            BlogAuthor blogAuthor = new BlogAuthor();
            blogAuthor.setId(author.getId());
            blogAuthor.setName(author.getName());
            blogAuthor.setSurname(author.getSurname());
            blogAuthor.setEmail(author.getEmail());
            blogAuthor.setAvatar(author.getAvatar());
            blogAuthor.setBirthDate(author.getBirthDate());
            authors.add(blogAuthor);
        }

        return authors;
    }

    public BlogPost save(BlogPostPayLoad payload) {
        Random rdm = new Random();
        Category[] categories = Category.values();

        BlogPost newBlogPost = new BlogPost();
        newBlogPost.setCategory(categories[rdm.nextInt(categories.length)]);
        newBlogPost.setCover(payload.getCover());
        newBlogPost.setContent(payload.getContent());
        newBlogPost.setReadingTime(payload.getReadingTime());
        
        Set<BlogAuthor> authors = fetchAuthors(rdm.nextInt(1, 4));
        newBlogPost.setAuthors(authors);

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

package jovelAsirot.U5W2D3.controllers;

import jovelAsirot.U5W2D3.entities.BlogAuthor;
import jovelAsirot.U5W2D3.services.BlogAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog/author")
public class BlogAuthorController {
    @Autowired
    private BlogAuthorService blogAuthorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogAuthor saveAuthor(@RequestBody BlogAuthor authorBody) {
        return this.blogAuthorService.save(authorBody);
    }

    @GetMapping
    public Page<BlogAuthor> getAllAuthor(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return this.blogAuthorService.getAll(page, size, sortBy);
    }

    @GetMapping("{authorId}")
    public BlogAuthor getSingleAuthor(@PathVariable Long authorId) {
        return this.blogAuthorService.findById(authorId);
    }

    @PutMapping("{authorId}")
    public BlogAuthor updateAuthor(@PathVariable Long authorId, @RequestBody BlogAuthor authorBody) {
        return this.blogAuthorService.updateById(authorId, authorBody);
    }

    @DeleteMapping("{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable Long authorId) {
        this.blogAuthorService.deleteById(authorId);
    }
}

package com.niutagodlewska.Blog2.Service;

import com.niutagodlewska.Blog2.Models.*;
import com.niutagodlewska.Blog2.ParseDataComment;
import com.niutagodlewska.Blog2.ParseData;
import com.niutagodlewska.Blog2.Repositories.CommentRepo;
import com.niutagodlewska.Blog2.Repositories.ArticleRepo;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private List<CommentCSV> comments = ParseDataComment.parse();
    private List<PostCSV> posts = ParseData.parse();

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ArticleRepo articleRepo;

    public CommentService() throws IOException {
    }

    public void saveCommentData() {
        PostCSV temp = new PostCSV();
        Article post = new Article();
        Iterable<Article> allPosts = articleRepo.findAll();
        for (CommentCSV c : comments) {
            Comment com = new Comment();
            for (PostCSV p : posts) {
                if (c.getIdPost().equals(p.getId())) {
                    temp = p;
                    break;
                }
            }
            for (Article p : allPosts) {
                if (p.getPostContent().equals(temp.getPostContent())) {
                    post = p;
                    break;
                }
            }
            com.setPost(post);
            com.setUsername(c.getUsername());
            com.setCommentContent(c.getCommentContent());
            commentRepo.save(com);
        }
    }

    public void addComment(CommentCSV comment, long id) {
        Article article = articleRepo.findById(id).get();
        Comment newCom = new Comment();
        newCom.setPost(article);
        newCom.setUsername(comment.getUsername());
        newCom.setCommentContent(comment.getCommentContent());
        commentRepo.save(newCom);
    }


    public List<Comment> getCommentById(long id) {
        Iterable<Comment> allCom = commentRepo.findAll();
        Article article = articleRepo.findById(id).get();
        List<Comment> comments = new LinkedList<>();
        for (Comment comment : allCom) {
            if (comment.getPost().getId() == id) {
                comments.add(comment);
            }
        }
        return comments;
    }

    public Comment getComment(long id) {
        Iterable<Comment> allCom = commentRepo.findAll();
        Comment thiscom = null;
        for (Comment comment : allCom) {
            if (comment.getPost().getId() == id) {
                thiscom = comment;
            }
        }
        return thiscom;
    }


    //metoda pozwala nam usunąć dany post z określonym id. Każdy post ma swoje id
    public void deleteComment(long id) {
        Optional<Comment> comm = commentRepo.findById(id);
        Comment comment = comm.get();
        commentRepo.delete(comment);
    }
}

package com.niutagodlewska.Blog2.Controller;
import com.niutagodlewska.Blog2.Models.Comment;
import com.niutagodlewska.Blog2.Repositories.ArticleRepo;
import com.niutagodlewska.Blog2.Repositories.CommentRepo;
import com.niutagodlewska.Blog2.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class CommentController {

    @Autowired
    private CommentRepo cs;
    @Autowired
    private ArticleRepo ps;

    @Autowired
    private CommentService commentService;

//
//    @GetMapping("/comment/add/{id}")
//    public String addComment(Model model, @PathVariable Long id){
//        model.addAttribute("article",ps.findById(id));
//        model.addAttribute("comments", new Comment());
//        return "comments/commentForm";
//    }

}

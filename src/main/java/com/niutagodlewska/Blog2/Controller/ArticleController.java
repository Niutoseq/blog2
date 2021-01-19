package com.niutagodlewska.Blog2.Controller;


import com.niutagodlewska.Blog2.Models.Article;
import com.niutagodlewska.Blog2.Models.Comment;
import com.niutagodlewska.Blog2.Models.CommentCSV;
import com.niutagodlewska.Blog2.Models.PostCSV;
import com.niutagodlewska.Blog2.Repositories.ArticleRepo;
import com.niutagodlewska.Blog2.Repositories.AuthorRepo;
import com.niutagodlewska.Blog2.Repositories.CommentRepo;
import com.niutagodlewska.Blog2.Service.ArticleService;
import com.niutagodlewska.Blog2.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.text.ParseException;

@Controller
public class ArticleController {

    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ArticleService as;

    @Autowired
    private CommentService cs;

    @GetMapping("/articles")
    public String home(Model model) {
        model.addAttribute("posts", articleRepo.findAll());
        return "article/articles";
    }

    @GetMapping("/articles/add")
    public String postCreate(Model model){
        model.addAttribute("post", new PostCSV());
//        model.addAttribute("posts", articleRepo.findAll());
        model.addAttribute("authors", authorRepo.findAll() );
        return "article/articleForm";
    }

    @PostMapping("/articles/add")
    public String postCreation(PostCSV post, Errors errors){
        if(errors.hasErrors()){
            return "article/articleForm";
        }
        as.addPost(post);

        return "redirect:/articles";
    }

    @GetMapping("/articles/delete/{id}")
    public String deletePost(@PathVariable long id){
        as.deletePost(id);
        //cs.deleteComment(id);

        return "redirect:/articles";
    }

    @GetMapping("/articles/detail/{id}")
    public String postDetailInfo(Model model, @PathVariable long id) throws ParseException {
        model.addAttribute("postDetail", articleRepo.findById(id).get());
        model.addAttribute("comments", cs.getCommentById(id));
        return "article/articleDetail";
    }

    @GetMapping("/articles/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        model.addAttribute("postDetail", articleRepo.findById(id).get());
        return "article/edit";
    }

    @PostMapping("/articles/edit/{id}")
    public String edited(@Valid Article article, Errors errors, @PathVariable long id){
        if(errors.hasErrors()){
            return "articles/edit";
        }
        as.editArticle(article, id);
        //po wykonaniu programu przenosimy się na "home". Home nie musi być w dokładnej ścieżce,
        //go jakimś cudem program zawsze znajduje. Html globalny??
        return("redirect:/articles");
    }




    @GetMapping("/comment/add/{id}")
    public String addComment(Model model, @PathVariable long id){
        model.addAttribute("article", articleRepo.findById(id).get());
        model.addAttribute("comment", new CommentCSV());
        return "comment/commentForm";
    }

    @PostMapping("/comment/add/{id}")
    public String commentCreate(CommentCSV comm, Errors errors,@PathVariable long id){
        if(errors.hasErrors()){
            return "comment/commentForm";
        }
        cs.addComment(comm, id);
        return "redirect:/articles";
    }


    @GetMapping("/comment/remove/{id}")
    public String deleteComment(@PathVariable long id){
        cs.deleteComment(id);
        return "redirect:/articles";
    }









    @GetMapping("/search")
    public String search(Model model) throws ParseException {
        model.addAttribute("article", new Article());
        return "search";
    }

    @PostMapping("/search")
    public String ListOfArticleContainWord(Model model, Article article) {
        String content = article.getPostContent();
        model.addAttribute("articles", as.getArticleListContainWord(content));
        return "searchresult";
    }


    @GetMapping("/searchauthor")
    public String searchforAuthor(Model model) throws ParseException {
        model.addAttribute("article", new Article());
        return "searchauthor";
    }

    @PostMapping("/searchauthor")
    public String ListOfArticleContainAuthor(Model model, Article article) {
        String content = article.getPostContent();
        model.addAttribute("articles", as.getArticleListContainAuthor(content));
        return "searchauthorresult";
    }

}

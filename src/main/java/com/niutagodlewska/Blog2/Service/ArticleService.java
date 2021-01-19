package com.niutagodlewska.Blog2.Service;

import com.niutagodlewska.Blog2.Models.*;
import com.niutagodlewska.Blog2.ParseData;
import com.niutagodlewska.Blog2.Repositories.AuthorRepo;
import com.niutagodlewska.Blog2.Repositories.CommentRepo;
import com.niutagodlewska.Blog2.Repositories.ArticleRepo;
import com.niutagodlewska.Blog2.Repositories.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class ArticleService {

    private List<PostCSV> posts = ParseData.parse();

    @Autowired
    private ArticleRepo articleRepo;
    @Autowired
    private TagRepo tagRepo;
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private CommentRepo commentRepo;


    public ArticleService() throws IOException {
    }

    public void savePostData() {
        for (int i = 0; i < posts.size(); i++) {
            Article p = new Article();

            List<Author> authors = new LinkedList<>();
            Iterable<Author> allAuthors = authorRepo.findAll();
            for (Author a : allAuthors){
                if (posts.get(i).getAuthors().contains(a.getName())){
                    authors.add(a);
                }
            }
            p.setAuthors(authors);

            List<Tag> tags = new LinkedList<>();
            Iterable<Tag> allTags = tagRepo.findAll();
            for (Tag t : allTags){
                String[] data = posts.get(i).getTags().split(" ");
                for (int j=0; j< data.length; j++){
                    if (data[j].equals(t.getTag())){
                        tags.add(t);
                    }
                }
            }
            p.setTags(tags);

            p.setPostContent(posts.get(i).getPostContent());

            articleRepo.save(p);
        }
    }

    public void addPost(PostCSV post) {
        Article newPost = new Article();
        List<Author> authors = new LinkedList<>();
        List<Tag> tags = new LinkedList<>();
        String[] data1 = post.getAuthors().split(", ");
        String[] data2 = post.getTags().split(" ");
        for (int i=0; i< data1.length; i++){
            for (Author author: authorRepo.findAll()){
                if (data1[i].equals(author.getName())){
                    authors.add(author);
                }
            }
        }
        for (int i=0; i< data2.length; i++){
            for (Tag tag: tagRepo.findAll()){
                if (data2[i].equals(tag.getTag())){
                    tags.add(tag);
                }
            }
            if (tags.size()!=i+1){
                Tag newTag = new Tag();
                newTag.setTag(data2[i]);
                tagRepo.save(newTag);
                tags.add(newTag);
            }
        }
        newPost.setAuthors(authors);
        newPost.setPostContent(post.getPostContent());
        newPost.setTags(tags);
        articleRepo.save(newPost);
    }

    public void deletePost(long id) {
        Optional<Article> p = articleRepo.findById(id);
        Article post = p.get();

        Iterable<Comment> comments = commentRepo.findAll();
        for (Comment comment: comments){
            if (comment.getPost() == post){
                commentRepo.delete(comment);
            }
        }

        articleRepo.delete(post);
    }

    public Article getPostById(long id) {
        Optional<Article> p = articleRepo.findById(id);
        Article post = p.get();
        return post;
    }

    public void editArticle(Article article, long id) {
        Optional<Article> a = articleRepo.findById(id);

        Article post = null;
        if (a.isPresent()) {
            post = a.get();
            post.setPostContent(article.getPostContent());
            post.setAuthors(article.getAuthors());
            post.setTags(article.getTags());
        }
        else{
            System.out.println("no problemo");
        }
        articleRepo.save(post);
    }


    public List<Article> getArticleListContainWord(String content) {
        Iterable<Article> posts = articleRepo.findAll();
        List<Article> list = new ArrayList<>();
        for (Article a : posts) {
            if (a.getPostContent().contains(content) || a.getTags().contains(content)) {
                list.add(a);
            }
        }
        return list;
    }

    public List<Article> getArticleListContainAuthor(String content) {
        Iterable<Article> post = articleRepo.findAll();
        List<Article> listA = new ArrayList<>();
        for (Article an : post) {
            if (an.getAuthors().contains(content)) {
                listA.add(an);
            }
        }
        return listA;
    }

}

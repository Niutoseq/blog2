package com.niutagodlewska.Blog2.Models;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentCSV {

    private String id;

    private String username;

    private String idPost;

    private String commentContent;
}
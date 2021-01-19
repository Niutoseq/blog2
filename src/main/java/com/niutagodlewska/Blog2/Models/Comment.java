package com.niutagodlewska.Blog2.Models;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private long id;

    private String username;

    @OneToOne(cascade = {CascadeType.MERGE})
    private Article post;

    @Column(length = 1000)
    private String commentContent;
}
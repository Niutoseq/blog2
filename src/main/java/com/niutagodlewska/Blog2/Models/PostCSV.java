package com.niutagodlewska.Blog2.Models;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class PostCSV {

    private String id;
    private String authors;
    private String postContent;
    private String tags;

}

package com.niutagodlewska.Blog2.Service;

import com.niutagodlewska.Blog2.Models.Author;
import com.niutagodlewska.Blog2.ParseData;
import com.niutagodlewska.Blog2.Repositories.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class AuthorService {

    private List<String> authors = ParseData.allAuthors();

    @Autowired
    private AuthorRepo authorRepo;

    public AuthorService() throws IOException {
    }

    public void saveAuthorData(){
        for (int i=0; i<authors.size(); i++){
            Author auth = new Author();

            auth.setName(authors.get(i));
            authorRepo.save(auth);
        }
    }
}

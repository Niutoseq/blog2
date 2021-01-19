package com.niutagodlewska.Blog2.Service;

import com.niutagodlewska.Blog2.Models.Tag;
import com.niutagodlewska.Blog2.ParseData;
import com.niutagodlewska.Blog2.Repositories.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TagService {

    private List<String> tags = ParseData.allTags();

    @Autowired
    private TagRepo tagRepo;

    public TagService() throws IOException {
    }

    public void saveTagData(){
        for (int i=0; i<tags.size(); i++){
            Tag tag = new Tag();
            tag.setTag(tags.get(i));
            tagRepo.save(tag);
        }
    }
}

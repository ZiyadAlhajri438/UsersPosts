package com.asistc.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostsModel implements Comparable<PostsModel> {

    private int id;
    private int user_id;
    private String title;
    private String body;


    @Override
    public int compareTo(PostsModel postsModel) {
        int compareId = postsModel.getId();
        return this.id - compareId;
    }
}

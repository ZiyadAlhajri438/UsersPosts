package com.asistc.demo.model;

import com.asistc.demo.entity.Comments;
import com.asistc.demo.entity.Posts;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserPostCommentModel {

    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;
    private List<Posts> posts;
    private List<Comments> comments;

}

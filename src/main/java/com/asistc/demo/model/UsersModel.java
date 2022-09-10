package com.asistc.demo.model;

import com.asistc.demo.entity.Comments;
import com.asistc.demo.entity.Posts;
import com.asistc.demo.entity.PostsRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsersModel {

    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;
    
}

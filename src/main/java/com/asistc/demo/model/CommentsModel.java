package com.asistc.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentsModel {
    private int id;
    private int post_id;
    private String name;
    private String email;
    private String body;

}

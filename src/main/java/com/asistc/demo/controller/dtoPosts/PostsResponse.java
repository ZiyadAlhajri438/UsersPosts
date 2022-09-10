package com.asistc.demo.controller.dtoPosts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostsResponse {

    private int id;
    private int user_id;
    private String title;
    private String body;
}

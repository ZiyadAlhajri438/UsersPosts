package com.asistc.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,String>{
    List<Posts> findAllByUserId(int userId);
}

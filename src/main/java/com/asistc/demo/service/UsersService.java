package com.asistc.demo.service;

import com.asistc.demo.entity.*;
import com.asistc.demo.model.CommentsModel;
import com.asistc.demo.model.PostsModel;
import com.asistc.demo.model.UserPostCommentModel;
import com.asistc.demo.model.UsersModel;
import javafx.geometry.Pos;
import lombok.RequiredArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RequiredArgsConstructor
@Service
@Log4j2
public class UsersService {
    private final UsersRepository usersRepository;
    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;

    public List<PostsModel> returnSortedPosts(){

        RestTemplate restTemplate= new RestTemplate();
        PostsModel[] allPosts = restTemplate.getForObject("https://gorest.co.in/public/v2/posts",PostsModel[].class);
        Arrays.sort(allPosts);
        return Arrays.asList(allPosts);
    }

    public String creatUsers(){
      //  List<Users> usersList = new ArrayList<>();
        RestTemplate restTemplate= new RestTemplate();
        UsersModel[] users = restTemplate.getForObject("https://gorest.co.in/public/v2/users",UsersModel[].class);
        for (int i=0 ; i < users.length;i++) {
            if (users[i].getGender().equalsIgnoreCase("male")) {
                this.usersRepository.save(new Users(users[i].getId(), users[i].getName(), users[i].getEmail()
                        , users[i].getGender(), users[i].getStatus()));
            }
        }
        return "users created";
    }

    public String creatPosts(){
        List<Users> usersList = this.usersRepository.findAll();
        RestTemplate restTemplate= new RestTemplate();
        PostsModel[] posts = restTemplate.getForObject("https://gorest.co.in/public/v2/posts",PostsModel[].class);
        for(Users user: usersList){
            for (PostsModel post: posts){
                if (user.getId() == post.getUser_id()){
                    this.postsRepository.save(new Posts(post.getId(), post.getUser_id(), post.getTitle()
                            , post.getBody()));
                }
            }
        }
        return "posts created";
    }

    public String creatComments(){
        List<Posts> postsList = this.postsRepository.findAll();
        RestTemplate restTemplate= new RestTemplate();
        CommentsModel[] commments = restTemplate.getForObject("https://gorest.co.in/public/v2/comments",CommentsModel[].class);
        for (Posts post: postsList) {
            for (CommentsModel comment: commments) {
                if (post.getId() == comment.getPost_id()){
                    this.commentsRepository.save(new Comments(comment.getId(),comment.getPost_id(),comment.getName(),
                            comment.getEmail(),comment.getBody()));
                }
            }
        }
        return "comments created";
    }

    public UserPostCommentModel getInfo (int userId){
        UserPostCommentModel userPostCommentModels= new UserPostCommentModel() ;
        Optional<Users> users= this.usersRepository.findById(userId);
        if (users.isPresent()) {
            userPostCommentModels.setId(users.get().getId());
            userPostCommentModels.setName(users.get().getName());
            userPostCommentModels.setEmail(users.get().getEmail());
            userPostCommentModels.setGender(users.get().getGender());
            userPostCommentModels.setStatus(users.get().getStatus());
            List<Posts> posts = this.postsRepository.findAllByUserId(users.get().getId());
            if (posts!=null && !posts.isEmpty()){
                userPostCommentModels.setPosts(posts);
                List<Comments> comments = this.commentsRepository.findAll();
                userPostCommentModels.setComments(new ArrayList<>());
                if (comments!=null && !comments.isEmpty()) {
                    for (Posts post : posts) {
                        for (Comments comment : comments) {
                            if (post.getId() == comment.getPost_id()) {
                                userPostCommentModels.getComments().add(comment);
                            }
                        }

                    }
                }
            }
        }
        return userPostCommentModels;
    }


    public String updateUser (String name , int id) {
        Optional<Users> user = this.usersRepository.findById(id);
        if (user.isPresent()) {
            user.get().setName(name);
            this.usersRepository.save(user.get());
            return "update done";
        }
        return "we didn't find it";
    }
}

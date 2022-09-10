package com.asistc.demo.controller;



import com.asistc.demo.controller.dto.dtoUsers.UpdateRequest;
import com.asistc.demo.controller.dtoPosts.PostsResponse;
import com.asistc.demo.model.PostsModel;
import com.asistc.demo.model.UserPostCommentModel;
import com.asistc.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsersController {

    @Autowired
   private UsersService usersService;

    @GetMapping(value =  "/allPosts" )
    public ResponseEntity<List<PostsResponse>> returnAllPosts (){

        return new ResponseEntity<>(this.usersService.returnSortedPosts().stream().map(postsModel -> new PostsResponse(postsModel.getId(), postsModel.getUser_id(),
                postsModel.getTitle(), postsModel.getBody())).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping(value =  "/allUsers" )
    public ResponseEntity<String> storeMaleToDb(){
        return new ResponseEntity<>(usersService.creatUsers() ,HttpStatus.OK) ;
    }
    @PostMapping(value = "/getUseresPosts")
    public ResponseEntity<String> storePosts(){
        return new ResponseEntity<>(usersService.creatPosts(),HttpStatus.OK) ;
    }
    @PostMapping(value = "/getAllComments")
    public ResponseEntity<String> storeComments(){
        return new ResponseEntity<>(usersService.creatComments(),HttpStatus.OK) ;
    }
    @GetMapping(value =  "/user/{userId}" )
    public ResponseEntity<UserPostCommentModel> getUserIfo (@PathVariable int userId){
        return new ResponseEntity<>(usersService.getInfo(userId),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateUserInfo")
    public ResponseEntity<String> updateUser(@RequestBody UpdateRequest updateRequest){
        return new ResponseEntity<>(usersService.updateUser(updateRequest.getName(),updateRequest.getId()),HttpStatus.OK);

    }


}

package com.CityThrillsMorocco.comment.Controller;

import com.CityThrillsMorocco.comment.Model.Comment;
import com.CityThrillsMorocco.comment.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/CityThrillsMorocco/Comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public List<Comment> getComments(){
        return commentService.getComments();
    }

    @GetMapping("/{activity_id}")
    public List<Comment> getCommentsByActivityId(@PathVariable("activity_id") Long activityId) {
        return commentService.getCommentsByActivityId(activityId);
    }

    @PostMapping("/{activity_id}")
    public Comment addComment(
            @RequestBody Comment comment,
            @PathVariable("activity_id") Long activityId,
            @RequestHeader("Authorization") String token) throws NoSuchAlgorithmException {
        return commentService.addComment(comment,activityId,token);
    }

    @PostMapping("/{parent_id}")
    public ResponseEntity<?> createReply(
            @RequestBody Comment reply,
            @RequestHeader("Authorization") String token,
            @PathVariable("parent_id") Long parentId
            ){
        commentService.createReply(reply,parentId,token);
        return ResponseEntity.ok().body("Reply Sent ");
    }

    @GetMapping("/{commentId}/replies")
    public List<Comment> getRepliesByCommentId(@PathVariable Long commentId) {
        return commentService.getRepliesByCommentId(commentId);
    }

    @DeleteMapping("/{id}")
    public void removeComment(@PathVariable("id") Long id ) throws NoSuchAlgorithmException{
        commentService.deleteComment(id);
    }

}

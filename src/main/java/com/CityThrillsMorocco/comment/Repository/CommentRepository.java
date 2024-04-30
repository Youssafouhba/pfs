package com.CityThrillsMorocco.comment.Repository;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.comment.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("SELECT comment.activity FROM Comment comment ORDER BY comment.note DESC ")
    List<Activity> findActivitiesWithHighRatings();


    @Query("SELECT ROUND(AVG(c.note),0) FROM Comment c WHERE c.activity.id = :activityId")
    Long findAverageNoteByActivityId(@Param("activityId") Long activityId);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.activity.id = :activityId")
    Long getNumberOfComments(@Param("activityId") Long activityId);




}

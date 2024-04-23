package com.CityThrillsMorocco.activity.Service;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Repository.ActivityRepo;
import com.CityThrillsMorocco.agence.Model.Agence;
import com.CityThrillsMorocco.agence.Service.AgenceService;
import com.CityThrillsMorocco.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ActivityService {

    private final ActivityRepo activityRepo;
    private final AgenceService agenceService;

    public List<Activity> getAllActivities(){
        List<Activity> activities = new ArrayList<Activity>( activityRepo.findAll());
        return activities;
    }

    public void deleteActivity(Long id){
         activityRepo.deleteById(id);
    }
    public Activity addActivity(Activity activity,Long agenceid){
        var existingActivity = activityRepo.selectExistsDesignation(activity.getDesignation());
        if(existingActivity) throw new BadRequestException(" this activity  already exists !!");
        Agence agence = agenceService.getAgenceById(agenceid);
        activity.setAgence(agence);
        activityRepo.save(activity);
        return activity;
    }

    public Activity getActivityById(Long id) {
        return activityRepo.getById(id);
    }
}

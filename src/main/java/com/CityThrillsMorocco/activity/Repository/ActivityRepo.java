package com.CityThrillsMorocco.activity.Repository;

import com.CityThrillsMorocco.activity.Model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepo extends JpaRepository<Activity, Long> {
    @Query(
            "" +
                    "SELECT CASE WHEN COUNT(u) > 0 THEN " +
                    "TRUE ELSE FALSE END " +
                    "FROM Activity u " +
                    "WHERE u.designation = ?1"
    )
    Boolean selectExistsDesignation(String designation);
    Activity findByDesignation(String designation);

    void deleteById(Long id);
}

package com.team12.foodforall.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author: Heng Gao
 * @date: 22/03/2022 11:59
 **/
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@DynamicInsert
@Entity(name="projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "title is necessary")
    String title;

    @Column(name = "content")
    @NotNull(message = "content is necessary")
    String content;

    @Lob
    @Column(length = Integer.MAX_VALUE, name = "img")
    String img;  // '/Foodforall.jpeg',


    @Column(nullable = false,columnDefinition = "INT default 0") // default value = 0 only no validation
    Integer achievedmeals;

    @NotNull(message = "target meals is necessary") // input validation + default value = 0 in db
    Integer targetmeals;

    @Column(nullable = false,columnDefinition = "DOUBLE default 0") // default value = 0 only no validation
    Float currentRevenue;

    @Column(nullable = false, columnDefinition = "FLOAT default 0") // default value = 0 only no validation any number value will be set to 0
    Float progress; //60,

    @NotNull(message = " country is necessary")
    String positionName; // 'UK',

    Float Lat;

    Float Lng; // <12.22, 23.55>

    @NotNull(message = "price is necessary")
    Float price; //'8.99',

    @Column(nullable = false, columnDefinition = "VARCHAR(10) default 'USD'") // default value = 0 only no validation any number value will be set to
    String currency; //"$"

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    public boolean isCompleted(){
        return this.achievedmeals == this.targetmeals;
    }

    public void updateTotalRevenue(){
        if(this.getAchievedmeals() == 0){
            return;
        }

        currentRevenue = (Float) (this.getAchievedmeals() * this.getPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Project project = (Project) o;
        return id != null && Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

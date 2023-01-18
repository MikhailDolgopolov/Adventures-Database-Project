package com.mikhaildolgopolov.spring.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
class VisitId implements Serializable{
    private int trip_point_id, sight_id;
}
@Entity
@Table(schema = "main", name = "visited_sights")
@IdClass(VisitId.class)
public class SightVisit {
    @Id
    @Getter @Setter
    private int trip_point_id;

    @Id
    @Getter @Setter
    private int sight_id;

    @Temporal(TemporalType.DATE)
    @Getter @Setter
    private Date visited_date;

}

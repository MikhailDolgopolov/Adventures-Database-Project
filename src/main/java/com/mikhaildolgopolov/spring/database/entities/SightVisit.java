package com.mikhaildolgopolov.spring.database.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
class VisitId implements Serializable{
    private int trippoint_id, sight_id;
}
@Entity
@Table(schema = "main", name = "visited_sights")
@IdClass(VisitId.class)
public class SightVisit {
    @Id
    @Getter @Setter
    private int trippoint_id;

    @Id
    @Getter @Setter
    private int sight_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Getter @Setter
    private Date visited_date;

    @Override
    public String toString() {
        return "SightVisit{" +
                "trippoint_id=" + trippoint_id +
                ", sight_id=" + sight_id +
                ", visited_date=" + visited_date +
                '}';
    }

}

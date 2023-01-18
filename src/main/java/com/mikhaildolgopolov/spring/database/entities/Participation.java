package com.mikhaildolgopolov.spring.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
class ParticipationId implements Serializable {
    private int person_id;
    private int trip_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipationId that = (ParticipationId) o;
        return person_id == that.person_id && trip_id == that.trip_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(person_id, trip_id);
    }
}
@Entity
@Table(schema = "main", name = "participation")
@IdClass(ParticipationId.class)
public class Participation {
    @Id
    @Getter @Setter
    private int person_id;

    @Id
    @Getter @Setter
    private int trip_id;
}

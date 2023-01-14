package com.mikhaildolgopolov.spring.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DeviationGroup {
    @Id
    private int grouping;
    private double st_deviation;

    public double getSt_deviation() {
        return st_deviation;
    }

    public void setSt_deviation(double st_deviation) {
        this.st_deviation = Math.round(st_deviation*100.0)/100.0;
    }

    public int getGrouping() {
        return grouping;
    }

    public void setGrouping(int grouping) {
        this.grouping = grouping;
    }
}

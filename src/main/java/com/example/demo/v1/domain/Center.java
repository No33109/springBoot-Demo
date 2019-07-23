package com.example.demo.v1.domain;

import com.example.demo.v1.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "center")
@Data
@NoArgsConstructor
public class Center extends BaseEntity {

    @Column(name = "name",length = 30)
    private String name;

    @Override
    public String toString() {
        return "Center{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}

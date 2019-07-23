package com.example.demo.v1.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
@NoArgsConstructor
public class BaseEntity implements Serializable {



    @Id
    @Column(name = "id",length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

}

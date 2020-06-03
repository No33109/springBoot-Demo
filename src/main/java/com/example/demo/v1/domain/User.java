package com.example.demo.v1.domain;

import com.example.demo.v1.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User extends BaseEntity {

    @NotBlank(message = "姓名不能为空")
    @Column(name = "name",length = 50)
    private String name;

    @NotBlank(message = "性别不能为空")
    @Column(name = "sex",length = 15)
    private String sex;


    @NotBlank(message = "电话号码不能为空")
    @Column(name = "mobile",length = 25)
    private String mobile;
}

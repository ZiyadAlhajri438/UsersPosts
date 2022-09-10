package com.asistc.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Users {
    @Id
    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;
}

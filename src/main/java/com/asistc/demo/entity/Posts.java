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
public class Posts {
    @Id
    private int id;
    private int userId;
    private String title;
    @Column(length = 8000)
    private String body;
    
}

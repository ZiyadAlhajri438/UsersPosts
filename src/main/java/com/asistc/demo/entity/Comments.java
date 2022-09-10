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
public class Comments {
    @Id
    private int id;
    private int post_id;
    private String name;
    private String email;
    private String body;
    
}

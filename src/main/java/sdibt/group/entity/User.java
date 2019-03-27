package sdibt.group.entity;

import javax.persistence.*;

/**
 * @author JacksonWang
 * @date 2019/3/27 14:59
 */
@Table(name = "springboot_jpa_user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

}

package com.liferon.countryapi.domain;

import com.liferon.countryapi.config.Auditable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String username;
    private String password;
    private String role;

//    @CreatedBy
//    @JsonIgnore
//    protected String createdBy;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
//    @CreatedDate
//    @Temporal(TIMESTAMP)
//    @JsonAlias(value = "created")
//    protected Date createdDate;
//
//    @LastModifiedBy
//    @JsonIgnore
//    protected String lastModifiedBy;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
//    @LastModifiedDate
//    @Temporal(TIMESTAMP)
//    @JsonIgnore
//    protected Date lastModifiedDate;
}

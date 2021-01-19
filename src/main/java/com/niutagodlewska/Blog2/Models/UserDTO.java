package com.niutagodlewska.Blog2.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name="\"user\"")
public class UserDTO {

    @Id
    @GeneratedValue
    private Long id;

    @Email(message="Enter a valid email address")
    private String email;

    @Length(min=6, message="Password must be at least 6characters long, contains any digits!")
    private String password;

    @Length(min=6, message="Password must be at least 6characters long, contains any digits!")
    private String rpassword;

    @Length(min=2, message="Username must be at least 2characters long")
    private String username;
    private boolean active;
    private String role;
}

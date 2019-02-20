package com.customerservice.customerservice;
import javax.persistence.*;

@Entity
public class Customer {
  @Id @GeneratedValue long id;
  public String firstName;
  public String lastName;
  public String dateOfBirth;
  @Column(unique=true)
  public String username;
  public String password;
  public Boolean deleted;
}
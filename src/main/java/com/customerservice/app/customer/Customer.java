package com.customerservice.app.customer;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Customer {
  @Id @GeneratedValue long id;
  public String firstName;
  public String lastName;
  @Temporal(TemporalType.DATE)
  public Date dateOfBirth;
  @Column(unique=true)
  public String username;
  public String password;
  public String deleted;
}
package com.ajustadoati.qr.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
  @Getter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Integer userId;

  private String firstName;

  private String lastName;

  private String numberId;

  private String mobileNumber;

  private String email;

  private Instant createdAt;

  private String password;

  private String company;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<Bank> banks;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  public Set<Role> getRoles() {
    return roles;
  }

  public List<Bank> getBanks() {
    return banks;
  }

  public String getCompany() {
    return company;
  }

  public String getPassword() {
    return password;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public String getEmail() {
    return email;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public String getNumberId() {
    return numberId;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setNumberId(String numberId) {
    this.numberId = numberId;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public void setBanks(List<Bank> banks) {
    this.banks = banks;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}

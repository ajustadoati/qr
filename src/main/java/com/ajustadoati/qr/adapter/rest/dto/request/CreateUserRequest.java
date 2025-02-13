package com.ajustadoati.qr.adapter.rest.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

  private Integer id;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  private String numberId;

  private String mobileNumber;

  private String company;

  private List<BankDto> banks;

  @Email
  private String email;

  @NotEmpty
  private Set<String> roles;

  public Integer getId() {
    return id;
  }

  public @NotNull String getFirstName() {
    return firstName;
  }

  public @NotNull String getLastName() {
    return lastName;
  }

  public @NotNull String getNumberId() {
    return numberId;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public String getCompany() {
    return company;
  }

  public List<BankDto> getBanks() {
    return banks;
  }

  public @Email String getEmail() {
    return email;
  }

  public @NotEmpty Set<String> getRoles() {
    return roles;
  }
}

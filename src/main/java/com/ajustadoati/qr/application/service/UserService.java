package com.ajustadoati.qr.application.service;


import com.ajustadoati.qr.adapter.rest.dto.request.CreateUserRequest;
import com.ajustadoati.qr.adapter.rest.repostory.BankRepository;
import com.ajustadoati.qr.adapter.rest.repostory.RoleRepository;
import com.ajustadoati.qr.adapter.rest.repostory.UserRepository;
import com.ajustadoati.qr.domain.Bank;
import com.ajustadoati.qr.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * User service class
 *
 * @author rojasric
 */
@Slf4j
@Service
public class UserService {

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final BankRepository bankRepository;

  public UserService(UserRepository userRepository, RoleRepository roleRepository,
    BankRepository bankRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.bankRepository = bankRepository;
  }

  public User createUser(CreateUserRequest createUserRequest) {
    log.info("Saving user {}", createUserRequest);
    var user = new User();
    user.setFirstName(createUserRequest.getFirstName());
    user.setLastName(createUserRequest.getLastName());
    user.setCompany(createUserRequest.getCompany());
    user.setNumberId(createUserRequest.getNumberId());
    user.setMobileNumber(createUserRequest.getMobileNumber());
    user.setEmail(createUserRequest.getEmail());
    user.setCreatedAt(Instant.now());

    var roles = roleRepository.findByRoleNames(createUserRequest.getRoles());
    user.setRoles(roles);

    var userResponse = userRepository.save(user);
    var banks = createUserRequest.getBanks().stream()
      .map(bankDto -> Bank.builder().name(bankDto.getName()).user(userResponse).build()).
      toList();

    user.setBanks(banks);

    bankRepository.saveAll(banks);

    return user;
  }

  public User getUserById(Integer id) {
    return userRepository
      .findById(id)
      .orElseThrow();
  }

  public User getUserByNumberId(String numberId) {
    return userRepository
      .findByNumberId(numberId)
      .orElseThrow();
  }

  public Page<User> getAllUsers(Pageable pageable) {
    return userRepository.findAll(pageable);
  }


  public void delete(Integer id) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

    userRepository.delete(user);

  }

}

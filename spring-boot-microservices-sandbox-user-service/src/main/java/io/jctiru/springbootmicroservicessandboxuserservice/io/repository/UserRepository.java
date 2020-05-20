package io.jctiru.springbootmicroservicessandboxuserservice.io.repository;

import org.springframework.data.repository.CrudRepository;

import io.jctiru.springbootmicroservicessandboxuserservice.io.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}

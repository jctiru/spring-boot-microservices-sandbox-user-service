package io.jctiru.springbootmicroservicessandboxuserservice.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.jctiru.springbootmicroservicessandboxuserservice.io.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findUserByEmail(String email);

}

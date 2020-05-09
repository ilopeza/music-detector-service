package com.musicinfofinder.musicdetectorsrv.repository;

import com.musicinfofinder.musicdetectorsrv.models.entities.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, String> {
}

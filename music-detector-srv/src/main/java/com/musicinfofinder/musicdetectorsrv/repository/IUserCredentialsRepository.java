package com.musicinfofinder.musicdetectorsrv.repository;

import com.musicinfofinder.musicdetectorsrv.models.entities.credentials.UserCredentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserCredentialsRepository extends CrudRepository<UserCredentials, String> {
}

package com.musicinfofinder.musicdetectorsrv.repository;

import com.musicinfofinder.musicdetectorsrv.models.entities.authentication.Authentication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepository extends CrudRepository<Authentication, String> {
}

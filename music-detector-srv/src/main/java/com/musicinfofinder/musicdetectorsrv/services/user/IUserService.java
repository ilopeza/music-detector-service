package com.musicinfofinder.musicdetectorsrv.services.user;

import com.musicinfofinder.musicdetectorsrv.exceptions.MalformedRequestException;
import com.musicinfofinder.musicdetectorsrv.exceptions.UserException;
import com.musicinfofinder.musicdetectorsrv.models.entities.user.User;
import com.musicinfofinder.musicdetectorsrv.models.response.dto.UserDTO;
import org.springframework.web.client.RestClientException;

import java.util.Optional;

public interface IUserService {
    /**
     * Makes a request to Spotify API to get the current user
     *
     * @param userId Spotify ID of the user
     * @return Current user logged in Spotify app.
     * @throws RestClientException       Will throw if the request fails.
     * @throws MalformedRequestException Will throw if the request does not pass validation
     */
    UserDTO requestCurrent(String userId) throws RestClientException, MalformedRequestException;

    /**
     * Saves user in database and returns the UserDTO.
     *
     * @param user user to be saved
     * @return UserDTO with the updated information
     * @throws UserException If the param is empty or not valid
     */
    UserDTO save(User user) throws UserException;

    /**
     * Finds the user by id in the database.
     *
     * @param id Id of the user
     * @return UserDTO if present. If not, will return empty
     * @throws UserException If the param is null or empty.
     */
    Optional<UserDTO> get(String id) throws UserException;
}

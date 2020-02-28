package com.birthdayupdateteam.birthdayupdateteambot.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.birthdayupdateteam.birthdayupdateteambot.model.Person;

@RepositoryRestResource(collectionResourceRel = "persons", path = "persons")
public interface PersonRepository extends PagingAndSortingRepository<Person, Integer> {
    Optional<Person> findByAuthCode(String authCode);
}

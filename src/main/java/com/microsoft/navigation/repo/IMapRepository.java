package com.microsoft.navigation.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microsoft.navigation.model.MapRequest;


@Repository
public interface IMapRepository extends CrudRepository<MapRequest, String> {

}

package com.microsoft.navigation.repo;

import org.springframework.data.repository.CrudRepository;

import com.microsoft.navigation.model.Map;

public interface IMapRepository extends CrudRepository<Map, String> {

}

package com.microsoft.navigation.repo;

import org.springframework.data.repository.CrudRepository;

import com.microsoft.navigation.model.Map;

public interface MapRepository extends CrudRepository<Map, String> {

}

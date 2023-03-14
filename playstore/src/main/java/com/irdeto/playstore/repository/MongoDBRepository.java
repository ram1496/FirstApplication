package com.irdeto.playstore.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.irdeto.playstore.datamodel.AppMetaData;

@Repository
public interface MongoDBRepository extends MongoRepository<AppMetaData, Integer> {

	@Query(value = "{ 'id' : ?0 }", fields = "{ 'id' : 0, 'appDescription' : 0, 'country' : 0}")
	List<AppMetaData> findByThePersonsFirstname();
}

package com.kokcuemre.springboot.soap.soapwebservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kokcuemre.springboot.soap.soapwebservices.domain.CountryEntity;


public interface CountryEntityRepository extends CrudRepository<CountryEntity, Long>{
	List<CountryEntity> findAll();
	Optional<CountryEntity> findByName(String name);
}

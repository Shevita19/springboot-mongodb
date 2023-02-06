package com.crud.springMongoDB.repository;

import com.crud.springMongoDB.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepository extends MongoRepository <Employee, Long> {


}

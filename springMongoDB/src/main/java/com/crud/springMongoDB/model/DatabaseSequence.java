package com.crud.springMongoDB.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "employee_Details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseSequence {

    private long seq;

    @Id
    private String id;

}

package com.example.demo.repository;

import com.example.demo.entity.BookType;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface BookTypeRepository extends Neo4jRepository<BookType, Long> {

    @Query("MATCH (a:BookType)-[:relateBooks]->(b) " +
            "WHERE a.typeName = $name " +
            "RETURN b "
    )
    List<BookType> findRelatedBookTypes(String name);

    @Query("MATCH (a:BookType)-[:relateBooks]->(b)-[:relateBooks]->(c) " +
            "WHERE a.typeName = $name " +
            "RETURN c "
    )
    List<BookType> findRbtofRbt(String name);

    BookType findBookTypeByTypeName(String typeName);
}

package com.mnb.repository;

import com.mnb.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query(value="Select * from product p where p.name LIKE %?1%",nativeQuery = true)
    List<Product> findByName(String keyword);

    @Query(value="Select * from product p where p.catalog_id =?",nativeQuery = true)
    List<Product> findByCatalog(int id);
}

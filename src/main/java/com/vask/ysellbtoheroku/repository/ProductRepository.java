package com.vask.ysellbtoheroku.repository;
import com.vask.ysellbtoheroku.model.Product;
import com.vask.ysellbtoheroku.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Optional<Product> findById(Integer id);
    void deleteById(Integer id);

    List<Product> findAllByUser(User user);


}

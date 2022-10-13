package com.vask.ysellbtoheroku.repository;
import com.vask.ysellbtoheroku.model.Bucket;
import com.vask.ysellbtoheroku.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BucketRepository extends JpaRepository<Bucket,Integer> {
    Optional<Bucket> findByUser(User user);
}

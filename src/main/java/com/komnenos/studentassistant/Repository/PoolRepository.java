package com.komnenos.studentassistant.Repository;

import com.komnenos.studentassistant.Entity.Pool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PoolRepository extends JpaRepository<Pool, Integer> {
    public Pool findTopByPoolId(int id);

    @Query(value = "select * from Pool where is_open=1 ", nativeQuery = true)
    public Pool findPoolByOpenIsTrue();
}

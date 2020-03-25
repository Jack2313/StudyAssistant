package com.komnenos.studentassistant.Repository;

import com.komnenos.studentassistant.Entity.Pool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoolRepository extends JpaRepository<Pool, Integer> {
    public Pool findTopByPoolId(int id);
}

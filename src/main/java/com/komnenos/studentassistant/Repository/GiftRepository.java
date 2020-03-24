package com.komnenos.studentassistant.Repository;

import com.komnenos.studentassistant.Entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GiftRepository extends JpaRepository<Gift, Integer> {
}

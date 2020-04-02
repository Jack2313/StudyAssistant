package com.komnenos.studentassistant.Repository;

import com.komnenos.studentassistant.Entity.Gift;
import com.komnenos.studentassistant.Entity.GiftRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiftRecordRepository extends JpaRepository<GiftRecord, Integer> {
    public List<GiftRecord> findAllByUserId(int uid);

    public GiftRecord findByGiftIdAndUserId(int gid, int uid);
}

package com.komnenos.studentassistant.Repository;

import com.komnenos.studentassistant.Entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Integer> {
}

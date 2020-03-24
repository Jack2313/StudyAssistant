package com.komnenos.studentassistant.Repository;

import com.komnenos.studentassistant.Entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Integer> {
    @Query("select c from Calendar c where c.userId=?1")
    public List<Calendar> getAllCalendar(int userId);

    @Query(value="select * from Calendar where is_able=0", nativeQuery = true)
    public List<Calendar> findFalseCalendar();

    public Calendar findByCalendarId(int id);
}

package com.komnenos.studentassistant.Repository;

import com.komnenos.studentassistant.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    public Ticket findByTicketId(int id);

    @Query(value = "select * from Ticket t where t.user_id=?1 and t.is_able=true", nativeQuery = true)
    public List<Ticket> getAllTicket(int userId);

    @Query(value = "select * from Ticket t where t.user_id=?1 and t.is_used=?2 and t.able=true", nativeQuery = true)
    public List<Ticket> getAllUserTicketWithB(int userId, boolean b);

    @Query(value = "select * from Ticket t where t.user_id=?1 and t.is_able=false", nativeQuery = true)
    public List<Ticket> getAllUnableTicket(int userId);
}

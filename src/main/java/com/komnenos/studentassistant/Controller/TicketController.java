package com.komnenos.studentassistant.Controller;

import com.komnenos.studentassistant.Entity.Ticket;
import com.komnenos.studentassistant.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.komnenos.studentassistant.StaticClass.Global;

@Controller
@CrossOrigin
@RequestMapping("/ticket/*")
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping(value = "/create")
    @ResponseBody
    public Ticket createTicket(@RequestParam("userId") int userId){
        Ticket t = new Ticket();
        t.setTicketCode(Global.getRandomString(16));
        t.setUsed(false);
        t.setAble(false);
        t.setValue(0);
        t.setUserId(userId);
        ticketRepository.save(t);
        ticketRepository.flush();
        return t;
    }

    @GetMapping(value = "/validate")
    @ResponseBody
    public int validateTicket(@RequestParam("ticketId") int ticketId){
        int result=0;
        Ticket t = ticketRepository.findByTicketId(ticketId);
        if(t!=null){
            t.setAble(true);
            result=1;
        }
        return result;
    }
}

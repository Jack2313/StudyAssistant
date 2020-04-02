package com.komnenos.studentassistant.Controller;

import com.komnenos.studentassistant.Entity.Ticket;
import com.komnenos.studentassistant.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.komnenos.studentassistant.StaticClass.Global;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/ticket/*")
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping(value = "/getUnusedTicketNum")
    @ResponseBody
    private int getUnusedTicketNum(@RequestParam("userId") int userId){
        return ticketRepository.getAllUserTicketWithB(userId, false).size();
    }

    @GetMapping(value = "/getUsedTicketNum")
    @ResponseBody
    private int getUsedTicketNum(@RequestParam("userId") int userId){
        return ticketRepository.getAllUserTicketWithB(userId, true).size();
    }


    @GetMapping(value = "/getUserMoney")
    @ResponseBody
    private String getUserMoney(@RequestParam("userId") int userId){
        double money = 0.0;
        List<Ticket> tickets=ticketRepository.getAllUserTicketWithB(userId, true);
        for(int i=0;i<tickets.size();i++){
            if(tickets.get(i).isGift()==false) {
                money = money + tickets.get(i).getValue();
            }
        }
        return Global.df.format(money);
    }

}

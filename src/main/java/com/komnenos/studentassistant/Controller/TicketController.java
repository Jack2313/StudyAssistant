package com.komnenos.studentassistant.Controller;

import com.komnenos.studentassistant.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping("/ticket/*")
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;


}

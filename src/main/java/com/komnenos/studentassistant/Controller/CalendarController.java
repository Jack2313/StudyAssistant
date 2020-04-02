package com.komnenos.studentassistant.Controller;

import com.komnenos.studentassistant.Entity.Calendar;
import com.komnenos.studentassistant.Entity.Ticket;
import com.komnenos.studentassistant.Entity.User;
import com.komnenos.studentassistant.Repository.CalendarRepository;
import com.komnenos.studentassistant.Repository.TicketRepository;
import com.komnenos.studentassistant.StaticClass.DateParser;
import com.komnenos.studentassistant.StaticClass.Global;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/calendar/*")
public class CalendarController {
    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private TicketRepository ticketRepository;

    private int createTicket(int userId, int number){
        for(int i=0;i<number;i++){
            Ticket t = new Ticket();
            t.setTicketCode(Global.getRandomString(16));
            t.setUsed(false);
            t.setAble(true);
            t.setGift(false);
            t.setPoolId(0);
            t.setValue(0.0);
            t.setUserId(userId);
            ticketRepository.save(t);
            ticketRepository.flush();
        }
        return 1;
    }

    private int validateTicket(int ticketId){
        int result=0;
        Ticket t = ticketRepository.findByTicketId(ticketId);
        if(t!=null){
            t.setAble(true);
            ticketRepository.save(t);
            result=1;
        }
        return result;
    }

    @GetMapping(value = "/getStudyTime")
    @ResponseBody
    public int getStudyTime(@RequestParam("userId") int userId){
        int time=0;
        List<Calendar> calendars = calendarRepository.getAllCalendar(userId);
        for(int i=0;i<calendars.size();i++){
            if(calendars.get(i).isAble()) {
                time = time + calendars.get(i).getHours();
            }
        }
        return time;
    }

    @GetMapping(value = "/getBonusCount")
    @ResponseBody
    public String getBonusCount(@RequestParam("userId") int userId){
        double result=0;
        List<Ticket> tickets= ticketRepository.getAllTicket(userId);
        for(int i=0;i<tickets.size();i++){
            if(tickets.get(i).isUsed()) {
                result = result + tickets.get(i).getValue();
            }
        }
        return Global.df.format(result);
    }

    @PostMapping(value ="/create")
    @ResponseBody
    public int register(@RequestBody Calendar calendar) {
        System.out.println(calendar.toString());
        int result = 0;
        Calendar c=new Calendar();
        c.setUserId(calendar.getUserId());
        c.setHours(calendar.getHours());
        c.setDescription(calendar.getDescription());
        c.setStartTime(calendar.getStartTime());
        Date d = DateParser.stringToDate(c.getStartTime());
        //System.out.println(DateParser.dateToString(DateParser.postHours(d,c.getHours())));
        c.setEndTime(DateParser.dateToString(DateParser.postHours(d,c.getHours())));
        c.setAble(false);
        calendarRepository.save(c);
        result = 1;
        return result;
    }

    @GetMapping(value = "/getViewList")
    @ResponseBody
    public List<Calendar> getViewList(){
        List<Calendar> calendars = calendarRepository.findFalseCalendar();
        return calendars;
    }

    @GetMapping(value = "/validate")
    @ResponseBody
    public int validate(@RequestParam("calendarId") int calendarId){
        Calendar c = calendarRepository.findByCalendarId(calendarId);
        c.setAble(true);
        createTicket(c.getUserId(),c.getHours());
        return 1;
    }


    @GetMapping(value="/setBondUser")
    @ResponseBody
    public int setBondUser(){
        Global.bondUser.put(4,5);
        Global.bondUser.put(5,4);
        return 1;
    }

    @GetMapping(value = "/getDayCalendar")
    @ResponseBody
    public List<Calendar> getDayCalendar(@RequestParam("userId") int userId, @RequestParam("date") String date){
        if(Global.bondUser.size()==0){
            setBondUser();
        }
        List<Calendar> calendars1 = calendarRepository.getAllCalendar(userId);
        List<Calendar> calendars2 = calendarRepository.getAllCalendar(Global.bondUser.get(userId));
        Date today=DateParser.stringToDate(date);
        Date eve=new Date();
        eve.setDate(today.getDate());
        eve.setYear(today.getYear());
        eve.setMonth(today.getMonth());
        eve.setSeconds(0);
        eve.setMinutes(0);
        eve.setHours(0);
        Date nextEve=new Date();
        nextEve.setTime(eve.getTime());
        nextEve.setHours(23);
        nextEve.setMinutes(59);
        nextEve.setSeconds(59);
        //System.out.println(eve.toString());
        //System.out.println(nextEve.toString());
        //System.out.println(DateParser.stringToDate("2020-03-28 08:03:21").toString());
        List<Calendar> results=new ArrayList<>();
        for(int i=0;i<calendars1.size();i++){
            if(nextEve.after(DateParser.stringToDate(calendars1.get(i).getStartTime()))){
                if(eve.before(DateParser.stringToDate(calendars1.get(i).getStartTime()))){
                    results.add(calendars1.get(i));
                }
            }
        }
        for(int i=0;i<calendars2.size();i++){
            if(nextEve.after(DateParser.stringToDate(calendars2.get(i).getStartTime()))){
                if(eve.before(DateParser.stringToDate(calendars2.get(i).getStartTime()))){
                    results.add(calendars2.get(i));
                }
            }
        }
        Collections.sort(results, (Calendar b1, Calendar b2)->b1.getStartTime().compareTo(b2.getStartTime()));
        return results;
    }
}

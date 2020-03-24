package com.komnenos.studentassistant.Controller;

import com.komnenos.studentassistant.Entity.Gift;
import com.komnenos.studentassistant.Entity.GiftRecord;
import com.komnenos.studentassistant.Entity.Pool;
import com.komnenos.studentassistant.Entity.Ticket;
import com.komnenos.studentassistant.Repository.GiftRecordRepository;
import com.komnenos.studentassistant.Repository.GiftRepository;
import com.komnenos.studentassistant.Repository.PoolRepository;
import com.komnenos.studentassistant.StaticClass.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.komnenos.studentassistant.StaticClass.Global;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/pool/*")
public class PoolController {
    @Autowired
    private PoolRepository poolRepository;

    @Autowired
    private GiftRepository giftRepository;

    @Autowired
    private GiftRecordRepository giftRecordRepository;

    @GetMapping(value = "/setGifts")
    @ResponseBody
    private List<Gift> setGifts(){
        List<Gift> gifts = new ArrayList<>();
        giftRepository.deleteAll();
        Gift g1 = new Gift();
        g1.setDescription("d");
        g1.setName("手绘板");
        g1.setValue(10);
        g1.setWeight(1);
        giftRepository.save(g1);

        Gift g2 = new Gift();
        g2.setDescription("d");
        g2.setName("肥餐");
        g2.setValue(999);
        g2.setWeight(1);
        giftRepository.save(g2);

        Gift g3 = new Gift();
        g3.setDescription("d");
        g3.setName("*肥");
        g3.setValue(999);
        g3.setWeight(1);
        giftRepository.save(g3);

        return giftRepository.findAll();
    }


    @GetMapping(value = "/draw")
    @ResponseBody
    private int draw(@RequestParam("userId") int userId){
        int result=0;
        Pool p = poolRepository.findTopByValueIsGreaterThan(0);
        int r=p.getRound();
        int v=p.getValue();

        return result;
    }

    @GetMapping(value = "/drawGift")
    @ResponseBody
    private int drawGift(@RequestParam("userId") int userId, @RequestParam("giftName") String giftName){
        int result=0;

        return result;
    }

    @GetMapping(value = "/getGifts")
    @ResponseBody
    private List<Gift> getGifts(){
        List<Gift> gifts = giftRepository.findAll();
        return gifts;
    }

    @GetMapping(value = "/getUserGifts")
    @ResponseBody
    private List<GiftRecord> getUserGifts(@RequestParam("userId") int userId){
        List<GiftRecord> records = giftRecordRepository.findAllByUserId(userId);
        return records;
    }

    @GetMapping(value = "/ExchangeGift")
    @ResponseBody
    private int ExchangeGift(@RequestParam("userId") int userId, @RequestParam("giftName") String giftName){
        List<GiftRecord> records = giftRecordRepository.findAllByUserId(userId);
        for(int i=0;i<records.size();i++){
            if(records.get(i).getGiftName()==giftName){
                int value=records.get(i).getValue();
                if(giftName.equals("手绘板")){
                    if(value>=10){
                        records.get(i).setValue(value-10);
                    }else{
                        return 0;
                    }
                }else if(giftName.equals("肥餐")){
                    if(value>=3){
                        records.get(i).setValue(value-3);
                    }else{
                        return 0;
                    }
                }else{
                    if(value>=50){
                        records.get(i).setValue(value-50);
                    }else{
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    @GetMapping(value = "/getPool")
    @ResponseBody
    private Pool getPool(){
        Pool pool = poolRepository.findTopByValueIsGreaterThan(0);
        if(check(pool)==1){
            pool=init();
        }
        return pool;
    }

    @GetMapping(value = "/getMessage")
    @ResponseBody
    private String getMessage(){
        return Global.message;
    }

    private int check(Pool pool){
        int result=0;
        Date d = DateParser.getCurrentDate();
        Date ed = DateParser.stringToDate(pool.getEndDate());
        if(d.after(ed)){
            result=1;
        }
        System.out.println("result:"+result);
        return result;
    }

    @GetMapping(value = "/init")
    @ResponseBody
    private Pool init(){
        Pool pool = poolRepository.findTopByValueIsGreaterThan(0);
            if(pool!=null){
            Global.message="上一期蛋池剩余："+pool.getValue()+"金";
            poolRepository.delete(pool);
        }
        Pool newPool = new Pool();
        Date sd= DateParser.getCurrentDate();
        newPool.setStartDate(DateParser.dateToString(sd));

        Date ed=DateParser.post(sd, 7);
        newPool.setEndDate(DateParser.dateToString(ed));

        newPool.setName("当前奖池");
        newPool.setRound(48);
        newPool.setValue(1000);

        poolRepository.save(newPool);
        return newPool;
    }
}

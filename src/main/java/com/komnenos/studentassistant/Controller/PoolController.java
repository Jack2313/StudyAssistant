package com.komnenos.studentassistant.Controller;

import com.komnenos.studentassistant.Entity.Pool;
import com.komnenos.studentassistant.Repository.PoolRepository;
import com.komnenos.studentassistant.StaticClass.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.komnenos.studentassistant.StaticClass.Global;
import java.util.Date;

@Controller
@CrossOrigin
@RequestMapping("/pool/*")
public class PoolController {
    @Autowired
    private PoolRepository poolRepository;

    @GetMapping(value = "/draw")
    @ResponseBody
    private int draw(@RequestParam("userId") int userId){
        int result=0;

        return result;
    }

    @GetMapping(value = "/getPool")
    @ResponseBody
    private Pool getPool(){
        Pool pool = poolRepository.findTopByValueIsGreaterThan(0);
        if(check(pool)!=1){
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

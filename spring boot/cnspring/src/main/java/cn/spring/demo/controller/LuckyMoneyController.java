package cn.spring.demo.controller;

import cn.spring.demo.entity.ErrorResult;
import cn.spring.demo.entity.LuckyMoney;
import cn.spring.demo.entity.Result;
import cn.spring.demo.repository.LuckyMoneyRepository;
import cn.spring.demo.service.LuckyMoneyService;
import cn.spring.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class LuckyMoneyController {

    @Autowired
    LuckyMoneyRepository luckyMoneyRepository;

    @Autowired
    LuckyMoneyService service;

    /**
     * 获取全部红包信息
     */
    @GetMapping("/luckyMoneys")
    public List<LuckyMoney> getAll() {
        return luckyMoneyRepository.findAll();
    }

    /**
     * 创建红包
     */
    @PostMapping("/luckyMoneys")
    public LuckyMoney setMoney(@RequestParam("money") BigDecimal money, @RequestParam("producer") String producer, @RequestParam("consumer") String consumer) {
        LuckyMoney luckyMoney = new LuckyMoney();
        luckyMoney.setMoney(money);
        luckyMoney.setConsumer(consumer);
        luckyMoney.setProducer(producer);
        return luckyMoneyRepository.save(luckyMoney);
    }

    @PostMapping("/give")
    public ErrorResult give(@Valid LuckyMoney luckyMoney, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
//            System.out.println(bindingResult.getFieldError().getDefaultMessage());
//            return null;

            return ResultUtils.Error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtils.success(luckyMoneyRepository.save(luckyMoney));
    }


    /**
     * 通过id来查询红包
     */
    @GetMapping("/luckyMoneys/{id}")
    public LuckyMoney getLuckyMoney(@PathVariable("id") Integer id) {
        //如果没有找到，就返回null
        return luckyMoneyRepository.findById(id).orElse(null);
    }

    /**
     * 更新红包
     */
    @PutMapping("luckyMoneys/{id}")
    public LuckyMoney updateMoney(@PathVariable("id") Integer id, @RequestParam("consumer") String consumer) {

        Optional<LuckyMoney> optional = luckyMoneyRepository.findById(id);
        //如果查询到数据
        if (optional.isPresent()) {
            LuckyMoney luckyMoney = optional.get();
            luckyMoney.setConsumer(consumer);
            return luckyMoneyRepository.save(luckyMoney);
        }
        return null;
    }

    @GetMapping("/luckyMoneys/two")
    public void creatTwo() {
        service.createTwo();
    }


    @GetMapping("/getMoney/{money}")
    public ErrorResult getMoney(@PathVariable("money") BigDecimal money) {
        return service.getMoney(money);
    }

}

package cn.spring.demo.service;

import cn.spring.demo.Exception.MoneyException;
import cn.spring.demo.entity.ErrorResult;
import cn.spring.demo.entity.LuckyMoney;
import cn.spring.demo.repository.LuckyMoneyRepository;
import cn.spring.demo.utils.ResultEnum;
import cn.spring.demo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class LuckyMoneyService {

    @Autowired
    LuckyMoneyRepository luckyMoneyRepository;

    //并在mysql数据库中将engine修改为INNODB
    @Transactional
    public void createTwo() {
        LuckyMoney luckyMoney = new LuckyMoney();
        luckyMoney.setProducer("九块九");
        luckyMoney.setMoney(BigDecimal.valueOf(520));

        LuckyMoney luckyMoneySecond = new LuckyMoney();
        luckyMoneySecond.setProducer("丧上数据库建立就看见了");
        luckyMoneySecond.setMoney(BigDecimal.valueOf(1314));

        luckyMoneyRepository.save(luckyMoney);
        luckyMoneyRepository.save(luckyMoneySecond);
    }

    public ErrorResult getMoney(BigDecimal money){
        if (money.intValue() <50) {
            throw new MoneyException(ResultEnum.MIN_MONEY);
        }
        return ResultUtils.success();
    }
}

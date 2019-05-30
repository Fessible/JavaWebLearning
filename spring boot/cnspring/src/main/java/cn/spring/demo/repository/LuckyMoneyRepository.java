package cn.spring.demo.repository;

import cn.spring.demo.entity.LuckyMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LuckyMoneyRepository extends JpaRepository<LuckyMoney, Integer> {
}

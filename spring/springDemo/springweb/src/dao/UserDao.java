package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

//数据库操作层
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    private HibernateTemplate hibernateTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }


    public void lessMoney() {
        String sql = "update account set salary=salary-? where username=?";
        jdbcTemplate.update(sql,  "1000","小王");
    }

    public void  moreMoney(){
        String sql = "update account set salary=salary+? where username=?";
        jdbcTemplate.update(sql,  "1000","小马");
    }
}

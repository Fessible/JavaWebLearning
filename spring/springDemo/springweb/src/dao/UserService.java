package dao;

import bean.User;
import org.springframework.transaction.annotation.Transactional;

//业务逻辑层
@Transactional
public class UserService {

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    public void accountMoney(){
        userDao.lessMoney();
        //异常
        int x = 100/0;
        userDao.moreMoney();
    }

    public void save(User user){
        userDao.getHibernateTemplate().save(user);
    }
}

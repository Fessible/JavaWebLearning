package test;

import entity.User;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import utils.HibernateUtils;

import java.util.List;

public class EntityTest {

    @Test
    public void testAdd() {
        //第一步加载核心配置文件

        //在hibernate里面封装对象
//        Configuration configuration = new Configuration();
//
//        configuration.configure();
//
//        //第二步 创建sessionFactory对象
//
//        //读取核心配置文件内容，创建sessionFactory
//        SessionFactory sessionFactory = configuration.buildSessionFactory();

        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        //第三步 使用SessionFactory创建session对象

        //类似连接
        Session session = sessionFactory.openSession();

        //第四步 开启事务
        Transaction transaction = session.beginTransaction();

        //第五步 写具体逻辑
//        User user = new User();
//        user.setUsername("张三");
//        user.setAddress("日本");
//        user.setPassword("1234");
//
//        //添加
//        session.save(user);
//
//        //查询操作
//        User user = session.get(User.class, 1);
//        System.out.println(user);

        //更新
        //删除
//        User user = session.get(User.class, 1);
//        session.delete(user);


        //第六步 提交事务
        transaction.commit();

        //第七步 关闭资源
        session.close();
        sessionFactory.close();

    }

    @Test
    public void testCache() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

//        User user = session.get(User.class, 2);
//        System.out.println(user);
//        User user2 = session.get(User.class, 2);
//        System.out.println(user2);

        User user = session.get(User.class, 2);
        user.setUsername("韩么么");

        transaction.commit();

        session.close();
        sessionFactory.close();
    }

    @Test
    public void testTransition() {
        SessionFactory sessionFactory = null;
        Session session = null;

        Transaction transaction = null;
        try {

            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();

            transaction = session.beginTransaction();

//            Query query = session.createQuery("from User");
//            List<User> userList = query.list();
//
//            System.out.println(userList);

            SQLQuery sqlQuery = session.createSQLQuery("select * from t_user");
//            List<Object[]> list = sqlQuery.list();
//            for (Object[] object : list) {
//                System.out.println(Arrays.toString(object));
//            }
            sqlQuery.addEntity(User.class);
            List<User> users = sqlQuery.list();
            System.out.println(users);

            transaction.commit();

        } catch (Exception o) {

            transaction.rollback();

        } finally {
            session.close();
            sessionFactory.close();
        }
    }


    @Test
    public void getLocalSession() {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, 2);
        System.out.println(user);
        transaction.commit();
    }
}

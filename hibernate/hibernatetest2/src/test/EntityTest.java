package test;

import entity.*;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import utils.HibernateUtils;

import java.util.List;

public class EntityTest {


    @Test
    public void ManyToMany(){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        //保存
        Course course = new Course();
        course.setCourseName("Java课");

        Course course1 = new Course();
        course1.setCourseName("政治课");


        Student student = new Student();
        student.setStudentName("小号");

        Student student1 = new Student();
        student1.setStudentName("皇后");

        //添加级联
        course.getStudentSet().add(student);
        course.getStudentSet().add(student1);

        course1.getStudentSet().add(student1);

        session.save(course);
        session.save(course1);

        transaction.commit();
        session.close();
        sessionFactory.close();

    }

    @Test
    public void changeSalesMan() {
        SessionFactory sessionFactory = null;
        Session session = null;

        Transaction transaction = null;
        try {

            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();

            transaction = session.beginTransaction();

            //首先获得客户的内容
            Customer customer = session.get(Customer.class, 2);

            //获得销售人员信息
            Salesman salesman = session.get(Salesman.class, 1);

            //相互设置
            customer.setSalesman(salesman);
            salesman.getCustomerSet().add(customer);


            transaction.commit();

        } catch (Exception o) {

            transaction.rollback();

        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    @Test
    public void testRelation() {
        SessionFactory sessionFactory = null;
        Session session = null;

        Transaction transaction = null;
        try {

            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();

            transaction = session.beginTransaction();

            //删除操作
            Salesman salesman = session.get(Salesman.class, 2);
            session.delete(salesman);


            //级联添加
//            Customer customer = new Customer();
//            customer.setcName("仿效三");
//
//            Customer customer2 = new Customer();
//            customer2.setcName("李晓五");
//
//
//            //销售人员
//            Salesman salesman = new Salesman();
//            salesman.setsName("小王");
//
//            salesman.getCustomerSet().add(customer);
//            salesman.getCustomerSet().add(customer2);
//
//            session.save(salesman);


            //相互操作
//            salesman.getCustomerSet().add(customer);
//            salesman.getCustomerSet().add(customer2);
//
//            customer.setSalesman(salesman);
//            customer2.setSalesman(salesman);


//            session.save(customer);
//            session.save(customer2);
//            session.save(salesman);

            transaction.commit();

        } catch (Exception o) {

            transaction.rollback();

        } finally {
            session.close();
            sessionFactory.close();
        }
    }

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

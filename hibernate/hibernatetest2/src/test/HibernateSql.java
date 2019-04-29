package test;

import entity.Student;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;
import utils.HibernateUtils;

import java.util.List;

public class HibernateSql {

    @Test
    public void queryTest() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

//        Query query = session.createQuery("from entity.User");
//        List<User> studentList = query.list();
//
//        System.out.println(studentList);

//        //条件查询
//        Query query = session.createQuery("from entity.User where username=?1 and uid=?2");
//
//        //设置查询的条件参数
//        query.setParameter(1, "韩么么");
//        query.setParameter(2, 2);
//
//        List<User> users = query.list();
//        System.out.println(users);

//        //模糊查询
//        Query query = session.createQuery("from  entity.User where  username like ?1");
//        query.setParameter(1, "%么%");
//
//        List<User> users = query.list();
//        System.out.println(users);

        //排序查询
//        Query query = session.createQuery("from  entity.User order by uid desc ");
//
//        System.out.println(query.list());

//
//        //分页查询
//        Query query = session.createQuery("from entity.User");
//
//        //设置分页数据
//        //设置开始位置
//        query.setFirstResult(0);
//        //设置每页记录数
//        query.setMaxResults(3);
//
//        System.out.println(query.list());
//
//        Query query = session.createQuery("select username from entity.User");
//        System.out.println(query.list());
////聚集函数的使用
//        Query query = session.createQuery("select  count(*) from entity.User");
//
//        Object o = query.uniqueResult();
//        //首先转换成long类型，再转换成int类型
//
//        Long l = (Long) o;
//        int count = l.intValue();
//        System.out.println(count);


        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}

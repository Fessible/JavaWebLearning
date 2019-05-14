package cm.mybatistest;

import cn.mybatis.entity.User;
import cn.mybatis.entity.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestMybatis {

    static Logger logger = LogManager.getLogger(TestMybatis.class);

    @Test
    public void test() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        System.out.println(sqlSessionFactory);
//        User user = sqlSession.selectOne("userMapper.selectUser",1);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findById(1);

        System.out.println(userMapper.getClass());
        System.out.println(user);

        //关闭
        sqlSession.close();
    }

    @Test
    public void testCRUD() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        SqlSession sqlSession = null;

        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //add
            User user = new User();
            user.setAddress("诶对了聊得来");
            user.setPassword("121343");
            user.setUsername("小红");
            userMapper.addUser(user);

            System.out.println(user.getUid());
//
            //delete
//            userMapper.delete(2);

            //update
//            User user = userMapper.findById(1);
//            user.setAddress("小黄人");
//            userMapper.update(user);

            sqlSession.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭
            sqlSession.close();
        }
    }

    @Test
    public void testParam() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        SqlSession sqlSession = null;

        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//            User user = userMapper.findByIdAndUsername(1, "小明");
//            System.out.println(user);

            //返回list
//            List<User> users = userMapper.findByUserLikeName("%小%");
//            System.out.println(users);

//            Map<String, Object> userMap = userMapper.getByMap(1);
//            System.out.println("结果： "+userMap);
//            Map<Integer, User> map = userMapper.getMapByName("%小%");
//            System.out.println(map);

            User user = userMapper.findById(2);
            System.out.println(user);

            sqlSession.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭
            sqlSession.close();
        }
    }

    @Test
    public void testMap() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        SqlSession sqlSession = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUser(2);
            System.out.println(user);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
}

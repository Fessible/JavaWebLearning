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
}

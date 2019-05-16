package cm.mybatistest;

import cn.mybatis.entity.User;
import cn.mybatis.entity.UserCacheMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class CacheTest {
    @Test
    public void test() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        SqlSession sqlSession = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            sqlSession = sqlSessionFactory.openSession();
            SqlSession sqlSession1 = sqlSessionFactory.openSession();

            UserCacheMapper userMapper = sqlSession.getMapper(UserCacheMapper.class);
            UserCacheMapper mapper = sqlSession1.getMapper(UserCacheMapper.class);

            User user = userMapper.findById(1);
            sqlSession.close();

            User user2 = mapper.findById(1);

            System.out.println(user == user2);
//            sqlSession.clearCache();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            sqlSession.close();
        }
    }
}

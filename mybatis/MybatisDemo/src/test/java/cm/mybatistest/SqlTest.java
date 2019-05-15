package cm.mybatistest;

import cn.mybatis.entity.User;
import cn.mybatis.entity.UserMapper;
import cn.mybatis.entity.UserSqlMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class SqlTest {

    @Test
    public void testSql() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        SqlSession sqlSession = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();

            UserSqlMapper userMapper = sqlSession.getMapper(UserSqlMapper.class);
//            List<User> list = userMapper.findByIdAndNameAndAddress(null, "%小%", null);
//            System.out.println(list);
            User user = new User();
//            user.setUid(1);
//            user.setPassword("3333");
//            List<User> byChoose = userMapper.findByChoose(user);
//            System.out.println(byChoose);

//            user.setUid(1);
//            user.setUsername("搭建安可咖啡");
//            userMapper.update2(user);

            List<User> list = userMapper.getByForEach(Arrays.asList(1, 4, 5));
            System.out.println(list);

            sqlSession.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
}

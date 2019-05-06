package test;

import bean.MyRowMapper;
import bean.User;
import com.mysql.cj.protocol.Resultset;
import dao.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.*;
import java.util.List;

public class jdbcTest {
    @Test
    public void add() {

        //设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        //创建jdbcTemplate对象，设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        //调用jdbcTemplate操作
        String sql = "insert into t_user (username,password,address)values(?,?,?)";
        int rows = jdbcTemplate.update(sql, "香雾", "22333", "往昔的");
        System.out.println(rows);
    }

    @Test
    public void update() {
        //设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        //创建jdbcTemplate对象，设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        //调用jdbcTemplate操作
        String sql = "update t_user set password=? where username=?";
        int rows = jdbcTemplate.update(sql, "hhhhh", "香雾");
        System.out.println(rows);
    }

    @Test
    public void delete() {
        //设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        //创建jdbcTemplate对象，设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        //调用jdbcTemplate操作
        String sql = "delete from t_user where username = ?";
        int rows = jdbcTemplate.update(sql, "香雾");
        System.out.println(rows);
    }

    @Test
    public void query(){
        //设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select count(*) from t_user";
        //调用jdbcTemplate的方法
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
    }

    @Test
    public void jdbcQuery(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;

        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            //创建连接
            connection = DriverManager.getConnection("jdbc:mysql:///test", "root", "root");

            //编写sql
            String sql = "select * from t_user where username=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "领导");

            resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                String username = resultset.getString("username");
                String password = resultset.getString("password");

                System.out.println(username);
                System.out.println(password);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void jdbcTemplateQuery(){
        //设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        //创建对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "select * from t_user where username = ?";
        User user = jdbcTemplate.queryForObject(sql, new MyRowMapper(), "领导");
        System.out.println(user);
    }

    @Test
    public void jdbcTemplateQueryList(){
        //设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        //创建对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "select * from t_user";

        List<User> list = jdbcTemplate.query(sql, new MyRowMapper());
        System.out.println(list);
    }

    @Test
    public void test(){
        ApplicationContext context = new  FileSystemXmlApplicationContext("/web/WEB-INF/applicationContext.xml");
        UserService service = (UserService) context.getBean("userService");
        JdbcTemplate jdbcTemplate = service.getUserDao().getJdbcTemplate();

        String sql = "select * from t_user";

        List<User> list = jdbcTemplate.query(sql, new MyRowMapper());
        System.out.println(list);
    }
}

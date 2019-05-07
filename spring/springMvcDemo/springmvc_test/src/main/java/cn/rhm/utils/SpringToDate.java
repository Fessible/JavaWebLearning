package cn.rhm.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SpringToDate  implements Converter<String, Date> {

    /**
     * 将string转换成对应的类型
     */
    @Override
    public Date convert(String s) {
        if (s == null) {
            throw new RuntimeException("参数不能为空");
        }

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //解析字符串
            Date date = dateFormat.parse(s);
            return  date;

        } catch (Exception e) {
            throw new RuntimeException("类型转换错误");
        }
    }
}

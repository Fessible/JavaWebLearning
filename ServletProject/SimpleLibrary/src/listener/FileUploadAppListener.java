package listener;

import utils.FileUploadAppProperties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class FileUploadAppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        InputStream in = getClass().getClassLoader().getResourceAsStream("upload.properties");
        Properties properties = new Properties();

        try {
            properties.load(in);
            for (Map.Entry<Object, Object> prop : properties.entrySet()) {
                String propertyName = (String) prop.getKey();
                String propertyValue = (String) prop.getValue();

                FileUploadAppProperties.getInstance().addProperty(propertyName, propertyValue);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

package pro.spring.prospring.ch6.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class DbConfigTest {
    @Test
    public void testOne() throws SQLException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class);
        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        testDataSource(dataSource);
        ctx.close();
    }

    private void testDataSource(DataSource dataSource) throws SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT 1");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int mockVal = resultSet.getInt(1);
                assertTrue(mockVal == 1);
            }
            statement.close();
        } catch (SQLException e) {
            log.debug("Can't execute SQL statement.");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}

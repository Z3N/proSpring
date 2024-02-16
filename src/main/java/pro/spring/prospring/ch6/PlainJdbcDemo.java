package pro.spring.prospring.ch6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.spring.prospring.ch6.dao.PlainSingerDao;
import pro.spring.prospring.ch6.dao.SingerDao;
import pro.spring.prospring.ch6.entities.Singer;

import java.util.List;

public class PlainJdbcDemo {
    private static SingerDao singerDao = new PlainSingerDao();
    private static Logger logger = LoggerFactory.getLogger(PlainJdbcDemo.class);

    public static void main(String[] args) {
        logger.info("Listing initial singer data:");
        listAllSingers();
        logger.info("-----------------------");
        logger.info("Inserting new singer");
        Singer singer = new Singer();
        singer.setFirstName("Ed");
        singer.setLastName("Sheeran");
        singer.setBirthDate(java.sql.Date.valueOf("1991-02-17"));
        singerDao.insert(singer);
        logger.info("Listing singer data after insert:");
        listAllSingers();
        logger.info("-----------------------");
        logger.info("Deleting the previous created user");
        singerDao.delete(singer.getId());
        logger.info("Listing singer data after delete:");
        listAllSingers();
    }

    private static void listAllSingers() {
        List<Singer> singers = singerDao.findAll();
        for (Singer singer : singers) {
            logger.info(singer.toString());
        }
    }
}

package pro.spring.prospring.ch7;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pro.spring.prospring.ch6.dao.SingerDao;
import pro.spring.prospring.ch6.entities.Singer;
import pro.spring.prospring.ch7.config.AppConfig;

import java.util.List;

@Slf4j
public class SpringHibernateDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        SingerDao singerDao = ctx.getBean("singerDaoCh7", SingerDao.class);
        singerDao.delete(1L);
        listSingers(singerDao.findAll());

        ctx.close();
    }

    private static void listSingers(List<Singer> singers) {
        log.info("---- List singers: ----");
        singers.forEach(s -> log.info(s.toString()));
    }

}

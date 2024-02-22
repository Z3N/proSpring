package pro.spring.prospring.ch8;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import pro.spring.prospring.ch8.config.JpaConfig;
import pro.spring.prospring.ch8.entity.Singer;
import pro.spring.prospring.ch8.service.SingerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class SingerJPATest {
    private GenericApplicationContext ctx;
    private SingerService singerService;

    @BeforeEach
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerService = ctx.getBean("jpaSingerService", SingerService.class);
        assertNotNull(singerService);
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerService.findAll();
        assertEquals(3, singers.size());
        listSingers(singers);
    }

    private static void listSingers(List<Singer> singers) {
        log.info("---- List singers: ----");
        singers.forEach(s -> log.info(s.toString()));
    }

    @AfterEach
    public void tearDown() {
        ctx.close();
    }
}

package bai.atm.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import bai.atm.Application;
import bai.atm.dao.LogMapper;
import bai.atm.model.Log;


/**
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class LogMapperTest {

  @Autowired
  private LogMapper logMapper;

  @Test
  public void insertLogTest() {
    Log log = new Log();
    log.setMethod("/test");
    log.setRequest("req");
    log.setResponse("rep");
    log.setStatus(200);
    logMapper.insertLog(log);
  }
}


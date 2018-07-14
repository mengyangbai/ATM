package Bai.ATM.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import bai.atm.dao.LogMapper;
import bai.atm.model.Log;


/**
 */
@RunWith(SpringRunner.class)
@Import(LogMapper.class)
public class LogMapperTest {

  @Autowired
  private LogMapper logmapper;

  @Test
  public void selectCityByIdTest() {
    Log log = new Log();
    log.setMethod("/test");
    log.setRequest("req");
    log.setResponse("rep");
    log.setStatus(200);
    logmapper.insertLog(log);
  }

}
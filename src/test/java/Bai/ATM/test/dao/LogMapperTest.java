package Bai.ATM.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import Bai.ATM.dao.LogMapper;
import Bai.ATM.model.Log;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link CityDao}.
 * @author wonwoo
 * @since 1.2.1
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
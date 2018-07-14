package bai.atm.test.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bai.atm.Application;
import bai.atm.dto.MessageDto;
import bai.atm.dto.UserDto;
import bai.atm.service.IAtmService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class IAtmServiceTest {

    @Autowired
    private IAtmService atmService;
    

    private UserDto user1;
    private UserDto user2;
    private UserDto user3;

    @Before
    public void before() throws Exception{
    	user1 = new UserDto();
    	user1.setMoney(0);
    	user1.setUsername("test");
    	user2 = new UserDto();
    	user2.setMoney(50);
    	user2.setUsername("test");
    	user3 = new UserDto();
    	user3.setMoney(15);
    	user3.setUsername("test");
    }
    @After
    public void after() throws Exception {
    }
    

    @Test
    public void dispenseTest() throws Exception {
    	MessageDto result1 = atmService.dispenseCash(user1);
    	MessageDto result2 = atmService.dispenseCash(user2);
    	MessageDto result3 = atmService.dispenseCash(user3);
    	
    	assert(result1.getCode()==30000);
    	assert(result2.getCode()==200);
    	assert(result3.getCode()==30001);
    }
    
}

package bai.atm.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bai.atm.dto.ListInfoDto;
import bai.atm.dto.MessageDto;
import bai.atm.dto.ReturnDtoInfo;
import bai.atm.dto.UserDto;
import bai.atm.exception.CustomException;
import bai.atm.model.Cash;
import bai.atm.model.Log;
import bai.atm.service.IAtmService;
import bai.atm.service.ILogService;

@RestController
@RequestMapping("/atm")
public class AtmController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AtmController.class);

	@Autowired
	private ILogService logService;
	
	@Autowired
	private IAtmService atmService;
	
    /**
     * @param response
     * @return ListInfoDto
     * @throws Exception
     */
	@RequestMapping(value="/info",method=RequestMethod.GET)
	public ReturnDtoInfo getAllCash(HttpServletResponse response) throws Exception{
		LOGGER.info("Getting all available cash...");
		ListInfoDto res = atmService.getAllCash();
		return res;
	}
	

    /**
     * @param money
     * @param response
     * @return
     * @throws Exception
     */
	@RequestMapping(value="/info/{value}",method=RequestMethod.GET)
	public ReturnDtoInfo getCountInfo(@PathVariable("value") Integer value, HttpServletResponse response) throws Exception{
		LOGGER.info("Getting count... Value==== {}", value);
		Cash cash = atmService.getCountByValue(value);		

		return cash;
	}
	
    /**
     * @param userDto
     * @param response
     * @return
     * @throws Exception
     */
	@RequestMapping(value="/dispense",method=RequestMethod.POST)
	public ReturnDtoInfo dispense(@RequestBody UserDto user, HttpServletResponse response) throws Exception{
		LOGGER.info("Dispensing cash... Money==== {}, Username==== {}", user.getMoney(),user.getUsername());
		MessageDto message = new MessageDto();
		
		try{
			boolean res = atmService.dispenseCash(user);
			Log log = new Log();
			log.setMethod("/dispense");
			log.setRequest(user.toString());
			log.setResponse("success");
			logService.insertLog(log);
			message.setData(user);
			message.setMessage(res?"success":"failure");
		}
		catch(CustomException e) {
			Log log = new Log();
			log.setMethod("/dispense");
			log.setRequest(user.toString());
			log.setResponse("failure");
			logService.insertLog(log);
			message.setData(user);
			message.setMessage("failure");
			message.setCode(500);
			
		}
		return message;
	}
	
	
	
}

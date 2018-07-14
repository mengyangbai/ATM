package Bai.ATM.service;

import Bai.ATM.dto.ListInfoDto;
import Bai.ATM.dto.UserDto;
import Bai.ATM.model.Cash;

public interface IATMService {
	
	ListInfoDto getAllCash();
	
	Cash getCountByValue(Integer value);
	
	boolean dispenseCash(UserDto user)throws Exception;

}

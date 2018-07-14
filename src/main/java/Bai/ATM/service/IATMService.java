package bai.atm.service;

import bai.atm.dto.ListInfoDto;
import bai.atm.dto.UserDto;
import bai.atm.model.Cash;

public interface IAtmService {
	
	ListInfoDto getAllCash();
	
	Cash getCountByValue(Integer value);
	
	boolean dispenseCash(UserDto user)throws Exception;

}

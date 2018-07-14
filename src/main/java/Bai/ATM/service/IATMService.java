package bai.atm.service;

import java.util.List;

import bai.atm.dto.ListInfoDto;
import bai.atm.dto.MessageDto;
import bai.atm.dto.ReturnDtoInfo;
import bai.atm.dto.UserDto;
import bai.atm.model.Cash;

public interface IAtmService {
	
	ListInfoDto getAllCash();
	
	Cash getCountByValue(Integer value);
	
	MessageDto dispenseCash(UserDto user)throws Exception;

}

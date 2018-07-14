package Bai.ATM.dao;

import java.util.List;

import Bai.ATM.model.Cash;

public interface CashMapper {
	List<Cash> getAllCash();
	
	Cash getCountByValue(Integer value);
	
	int dispenseCash(Cash cash);
}

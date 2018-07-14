package bai.atm.dao;

import java.util.List;

import bai.atm.model.Cash;

public interface CashMapper {
	List<Cash> getAllCash();
	
	Cash getCountByValue(Integer value);
	
	int dispenseCash(Cash cash);
}

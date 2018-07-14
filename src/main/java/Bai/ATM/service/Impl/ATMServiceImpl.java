package bai.atm.service.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import bai.atm.dao.CashMapper;
import bai.atm.dto.ListInfoDto;
import bai.atm.dto.MessageDto;
import bai.atm.dto.ReturnDtoInfo;
import bai.atm.dto.UserDto;
import bai.atm.exception.CustomException;
import bai.atm.model.Cash;
import bai.atm.service.IAtmService;

@Service
public class AtmserviceImpl implements IAtmService{

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AtmserviceImpl.class);
	
	@Autowired
	private CashMapper cashMapper;

	public ListInfoDto getAllCash() {
		ListInfoDto res = new ListInfoDto();
		List<Cash> cash = cashMapper.getAllCash();
		res.setList(cash);
		res.setTotal(cash.size());
		return res;
	}

	@Transactional
	public MessageDto dispenseCash(UserDto user) throws Exception {
		
		LOGGER.info("Starting dispense.... User===={}, Money===={}",user.getUsername(),user.getMoney());
		MessageDto messageDto = new MessageDto();
		//check first
		if(user.getMoney()==null || user.getMoney()<=0) {
			messageDto.setCode(30000);
			messageDto.setMessage("please input a valid money. money > 0");
			return messageDto;
		}
		
		
		List<Cash> cash = cashMapper.getAllCash();
		
		List<Cash> withdrawCash = withdraw(user.getMoney(),cash);
		LOGGER.info("ATM is able to dispence.... process begin..");
		if(!CollectionUtils.isEmpty(withdrawCash)) {
			messageDto.setData(withdrawCash);
			for(Cash singleNote: withdrawCash) {
				cashMapper.dispenseCash(singleNote);			
			}
			messageDto.setMessage("success");		
		}
		else{
			messageDto.setCode(30001);
			messageDto.setMessage("insufficient cash");
		}

		LOGGER.info("Process end..");
		return messageDto;
	}

	private List<Cash> withdraw(Integer money, List<Cash> cash) {
		/***
		 * Use dp to solve this
		 */
		int[] dp = new int[money+1];
		for(int i = 1; i <= money; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		
		List<Integer> stack = new ArrayList<Integer>();
		for(Cash singleCash:cash) {
			for(int i = 0; i < singleCash.getCount();i++) {
				stack.add(singleCash.getValue());
			}
		}
		
		dp[0] = 0;
		
		for(int i = 1; i<=money;i++) {
			for(Integer value: stack) {
				if(i - value >= 0 && dp[i - value] <= value) {
					dp[i] = Math.min(dp[i-value]+1, dp[i]);					
				}
			}
		}
		
		int[] stack_reverse = new int[stack.size()];
		for(int i = stack.size()-1; i>=0;i--) {
			stack_reverse[stack.size()-1-i] = stack.get(i);
		}
		
		if(dp[money] <= money) {
			//use dfs to get the real result
			List<List<Integer>> res = new ArrayList<List<Integer>>();
			List<Integer> order = new ArrayList<Integer>();
			
			withDrawHelper(money, stack_reverse,order, res);
			
			Map<Integer,Integer> map = new HashMap<Integer,Integer>();
			
			for(Integer n: res.get(0)) {
				if(map.containsKey(n)) {
					map.put(n, map.get(n)+1);
				}
				else {
					map.put(n, 1);
				}
			}
			
			List<Cash> withDrawCash = new ArrayList<Cash>();
			for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
				Cash singlecash = new Cash();
				singlecash.setValue(entry.getKey());
				singlecash.setCount(entry.getValue());
				withDrawCash.add(singlecash);
			}
			return withDrawCash;
			
		}
		else {
			return null;
		}
	}

	private static void withDrawHelper(Integer money, int[] stack, List<Integer> order,List<List<Integer>> res) {
		if(money == 0) {
			ArrayList<Integer> neworder = new ArrayList<Integer>();
			for(Integer tmp:order) {
				neworder.add(new Integer(tmp));
			}
			res.add(neworder);
			return;
		}
		if(res.size()!=0) {
			return;
		}
		for(int i = 0; i < stack.length; i++) {
			if(money-stack[i] >=0) {
				int tmp = stack[i];
				order.add(tmp);						
				stack[i] = Integer.MAX_VALUE;
				withDrawHelper(money-tmp,stack,order,res);
				order.remove(order.size() - 1);
				if(res.size()!=0) {
					return;
				}
				stack[i] = tmp;
			}
		}
		return;				
	}


	public Cash getCountByValue(Integer value) {
		return cashMapper.getCountByValue(value);
	}

}

package Bai.ATM.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Bai.ATM.dao.LogMapper;
import Bai.ATM.model.Log;
import Bai.ATM.service.ILogService;

@Service
public class LogServiceImpl implements ILogService{
	@Autowired
	private LogMapper logMapper;

	public Integer insertLog(Log log) {
		int res = logMapper.insertLog(log);
		return res;
	}

}

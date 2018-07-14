package bai.atm.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bai.atm.dao.LogMapper;
import bai.atm.model.Log;
import bai.atm.service.ILogService;

@Service
public class LogServiceImpl implements ILogService{
	@Autowired
	private LogMapper logMapper;

	public Integer insertLog(Log log) {
		int res = logMapper.insertLog(log);
		return res;
	}

}

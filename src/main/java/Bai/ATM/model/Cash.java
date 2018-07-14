package bai.atm.model;

import bai.atm.dto.ReturnDtoInfo;

public class Cash implements ReturnDtoInfo{
	private Integer value;
	private Integer count;
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}

}

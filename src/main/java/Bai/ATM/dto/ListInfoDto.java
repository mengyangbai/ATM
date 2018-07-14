package Bai.ATM.dto;

public class ListInfoDto implements ReturnDtoInfo{
	private Integer total;
	private Object list;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Object getList() {
		return list;
	}
	public void setList(Object list) {
		this.list = list;
	}

}

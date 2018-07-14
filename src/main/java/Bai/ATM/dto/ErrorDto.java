package Bai.ATM.dto;

public class ErrorDto implements ReturnDtoInfo {
	private Integer code=200;                   //默认成功状态码
	private String message;
	
	private Object data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}


}

package Bai.ATM.exception;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import Bai.ATM.dto.ErrorDto;

@RestController
public class GlobalExceptionHandler implements ErrorController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GlobalExceptionHandler.class);

	private static final String PATH = "/error";
	
	@Autowired
	private ErrorAttributes errorAttributes;

	@RequestMapping(value = { "${error.path:/error}" }, produces = { "text/html" })
	public ErrorDto errorHtml(HttpServletRequest request,HttpServletResponse response) throws IOException {
		return error(request, response);
	}

	@RequestMapping({ "${error.path:/error}" })
	@ResponseBody
	public ErrorDto error(HttpServletRequest request,
		HttpServletResponse response) throws IOException {
		ErrorDto info = new ErrorDto();
		Map<String,Object> errorAttributes = getErrorAttributes(request, true);
		// System.out.println(errorAttributes);
        Integer status=(Integer)errorAttributes.get("status");
        String path=(String)errorAttributes.get("path");
        String trace=(String)errorAttributes.get("trace");
        String exception=(String)errorAttributes.get("exception");
        String messageFound=(String)errorAttributes.get("message");
       if(messageFound.indexOf("Access Denied")!=-1){
        	status=401;
        	messageFound="AccessDenied";
        }
        else if(messageFound.indexOf("Unauthorized")!=-1){
        	status=401;
        }
        
        else if(messageFound.contains("Expired or invalid JWT token")){
        	status=1401;
        	messageFound="ExpiredToken";
        }
        else if(messageFound.contains("logged")){
        	status=1402;
        	messageFound="logged";
        }
        else {
        	messageFound=dealWithExceptionContent(messageFound);
        }
        
        String allMessage="\nMessage:"+messageFound+".\n When request path:"+path+". Here produces an exception:" + exception + ".\nTrace:\n"+trace;
		// 打印异常信息：
		info.setCode(status);
		info.setMessage(lcaolGeMessage(messageFound));
		LOGGER.error(allMessage);
		return info;
	}
	/**
	 * 处理异常信息
	 */
	public String lcaolGeMessage(String messageString){
		if(messageString.indexOf(":")!=messageString.lastIndexOf(":")){
			messageString=messageString.substring(0,messageString.indexOf(":"))+". "+messageString.substring(messageString.indexOf(":")+1).substring(0,messageString.substring(messageString.indexOf(":")+1).indexOf(":"));
			
		}
		return messageString;
	}
	/**
	 * 异常内容处理
	 * @param messageFound
	 * @return
	 */
	public String dealWithExceptionContent(String messageFound){
		if(messageFound.equalsIgnoreCase("No message available")){
        	messageFound="AccessDenied";
        }
        else if(messageFound.indexOf("PersistenceException")!=-1){
        	messageFound="DataBaseError";
        }

        else if(messageFound.indexOf("NumberFormatException")!=-1){
        	messageFound="NumberFormatException";
        }
        else if(messageFound.indexOf("Can not")!=-1&&messageFound.indexOf("java.util.Date")!=-1){
        	messageFound="DateFormatError";
        }
        else if(messageFound.indexOf("connect")!=-1){
        	messageFound="ConnectError";
        }
        else if(messageFound.indexOf("NullPointerException")!=-1){
        	messageFound="NullPointerException";
        }
		return messageFound;
	}
	
	public String getErrorPath() {
		return PATH;
	}
	/**
	 * 根据请求处理数据
	 * @param request
	 * @param includeStackTrace
	 * @return
	 */
	private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        
	  	RequestAttributes requestAttributes = new ServletRequestAttributes(request);
	  	//System.out.println(requestAttributes.get);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }
}

package cybersoft.javabackend.java16giranghia.common.util;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ResponseHelper {
	public static ResponseEntity<Object> getResponse(Object obj, HttpStatus status){
		// Tao kieu du lieu voi Map data
		Map<String, Object> map = new HashMap<String, Object>();
		// tra ve noi dung cua http response & value cua Httpstatus code & hash Errors
		map.put("content", obj);
		map.put("hashErrors", false);
		map.put("errors", "");
		map.put("timestamp", LocalDateTime.now());
		map.put("status", status.value());
		
		return new ResponseEntity<Object>(map, status);
	}
	
	public static ResponseEntity<Object> getErrosResponse(BindingResult errors, HttpStatus status){
		// Tao kieu du lieu voi Map data
		Map<String, Object> map = new HashMap<String, Object>();
		// tra ve noi dung cua http response & value cua Httpstatus code & hash Errors
		map.put("content", null);
		map.put("hashErrors", true);
		map.put("errors", ErrorHelper.getAllError(errors));
		map.put("timestamp", LocalDateTime.now());
		map.put("status", status.value());
		
		return new ResponseEntity<Object>(map, status);
	}
	
	public static ResponseEntity<Object> getErrosResponse(String errors, HttpStatus status){
		// Tao kieu du lieu voi Map data
		Map<String, Object> map = new HashMap<String, Object>();
		// tra ve noi dung cua http response & value cua Httpstatus code & hash Errors
		map.put("content", null);
		map.put("hashErrors", true);
		map.put("errors", errors);
		map.put("timestamp", LocalDateTime.now());
		map.put("status", status.value());
		
		return new ResponseEntity<Object>(map, status);
	}
}

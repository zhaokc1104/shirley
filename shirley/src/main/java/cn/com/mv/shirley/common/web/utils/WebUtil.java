package cn.com.mv.shirley.common.web.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WebUtil {
	
	public static void responseJson(HttpServletResponse response, Object jsonObject)
            throws IOException {
		responseJson(response, jsonObject , null);
    }
	
	public static void responseJson(HttpServletResponse response, Object jsonObject, String jsonpCallback)
            throws IOException {
		
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        Object result = jsonObject;
        if (jsonObject instanceof String) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("result", jsonObject);
            result = resultMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(result);
        
        String resp = jsonStr;
        
        if (StringUtils.isEmpty(jsonpCallback)) {
        	resp = jsonpCallback+"("+jsonStr+")";
        }
        
        response.getWriter().write(resp);
        response.getWriter().flush();
    }
	
	
}


package com.sn.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {  
    public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }
    public String[] getParameterValues(String parameter) {
      String[] values = super.getParameterValues(parameter);
      if (values==null)  {
                  return null;
          }
      int count = values.length;
      String[] encodedValues = new String[count];
      for (int i = 0; i < count; i++) {
                 encodedValues[i] = cleanXSS(values[i]);
       }
      return encodedValues;
    }
    public String getParameter(String parameter) {
          String value = super.getParameter(parameter);
          if (value == null) {
                 return null;
                  }
          return cleanXSS(value);
    }
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null){
        	return null;
        }
        if(sqlValidate(value)){
        	return null;
        }
        return cleanXSS(value);
    }
    /**
     * @see 处理脚本注入
     * @param value
     * @return
     */
    private String cleanXSS(String value) {
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }
    /**
     * @see 处理sql注入
     * @param value
     * @return
     */
    public static boolean sqlValidate(String str) {
	    str = str.toLowerCase();//统一转为小写
	   /* String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
	    		"char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
	    		"table|from|grant|use|group_concat|column_name|" +
	             "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
	            "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";//过滤掉的sql关键字，可以手动添加
*/	    
	    String badStr = "'select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute'";
	    String[] badStrs = badStr.split("\\|");
	    for (int i = 0; i < badStrs.length; i++) {
	        if (str.indexOf(badStrs[i]) >= 0) { 
	            return true;
	        }
	    }
	    return false;
	}
} 
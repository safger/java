package com.sn.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class UUIDCreater {

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	public static String test() {
		return "12312";
	}

	public static void main(String[] args) throws Exception {
	/*	System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));
		System.out.println(new java.sql.Date(System.currentTimeMillis()));
		System.out.println(new java.util.Date(System.currentTimeMillis()));
		 SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm"); 
	        Date date = new Date(); 
	         //按照规定格式输出当前时间
	        System.out.println(bartDateFormat.format(date)); //200810080913
	        try {
	            //读入时间,转化为时间类型，并且格式化输出
	            System.out.println(bartDateFormat.format(bartDateFormat.parse("2008-10-07 14-56"))); // 200810071456
	        } catch (ParseException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } 
	         
	        System.out.println(mapXy.getX("120.070527"));
	       // System.out.println(mapXy.getY("30.169382"));
	        System.out.println(mapXy.getY("30.169457"));*/
		//System.out.println(new Date());
		/*JaxWsProxyFactoryBean  factoryBean=new JaxWsProxyFactoryBean(); 
        factoryBean.getInInterceptors().add(new LoggingInInterceptor()); 
        factoryBean.getOutInterceptors().add(new LoggingOutInterceptor()); 
        factoryBean.setServiceClass(RemoteInterface.class); 
        factoryBean.setAddress("http://120.199.15.244:8080/services/InterfaceXML"); 
        RemoteInterface impl=(RemoteInterface) factoryBean.create(); 
        int a=impl.SyncOrderState("1360024396352", "016010", "test");
        System.out.println("----------"+a);*/
       /* Affairs affairs=new Affairs();
        affairs.setFuid("22");
        Gson gson = new Gson();
        String para = gson.toJson(affairs);
        System.out.println(.l);*/

		/*Boolean ctime=true;
		SimpleDateFormat localTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sdate = localTime.parse("2015-07-19 07:00:00");
		Date edate=localTime.parse("2015-08-19 22:00:00");
		long time = System.currentTimeMillis();
		if(time>=sdate.getTime()&& time<=edate.getTime()){
			 Calendar cal = Calendar.getInstance();
			 cal.setTime(new Date());
			 //判断周1-3
			 System.out.println("----------"+(cal.get(Calendar.DAY_OF_WEEK)-1));
			 if(cal.get(Calendar.DAY_OF_WEEK)-1>0&&cal.get(Calendar.DAY_OF_WEEK)-1<=3){
				 //判断 7-22点
				 if((cal.get(Calendar.DAY_OF_WEEK)-1<=1&&cal.get(Calendar.HOUR_OF_DAY)<7)||(cal.get(Calendar.DAY_OF_WEEK)-1>=3&&cal.get(Calendar.HOUR_OF_DAY)>=22)){
				 }else{
					 ctime=false;
				 }
			 }
		}
			   
		
		 System.out.println("----------"+ctime);*/
		 
		
	}
}
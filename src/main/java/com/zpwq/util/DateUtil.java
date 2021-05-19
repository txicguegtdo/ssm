package com.zpwq.util;

import java.io.FileInputStream;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import com.alibaba.fastjson.JSONObject;

public class DateUtil {
	private static Properties properties;
	private static String spec_day;
	static{
        try{
            String path = DateUtil.class.getClassLoader().getResource("").getPath();
            path = URLDecoder.decode(path,"UTF-8");
            System.out.println("根目录:"+path);
            properties = new Properties();
            properties.load(new FileInputStream(path+"init.properties"));
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            spec_day = properties.getProperty(String.valueOf(calendar.get(Calendar.YEAR)));
            String file_name = properties.getProperty("mail_file_name");
            String mail_file = path+file_name;
            mail_file = URLDecoder.decode(mail_file,"UTF-8");
            properties.setProperty("mail_file",mail_file);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	public static Map<String,Object> getDayInfo(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<Integer> gongzuo = new ArrayList<Integer>();
        List<Integer> xiuxi = new ArrayList<Integer>();

        JSONObject json = JSONObject.parseObject(spec_day);
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        //calendar.set(Calendar.MONTH,8);
        //calendar.set(Calendar.DATE,31);
        String date = DateFormat.getDateInstance().format(calendar.getTime());
        int maxDay = calendar.getActualMaximum(Calendar.DATE);
        int today  = calendar.get(Calendar.DATE) ;
        int xiqi,mon,day;
        String time;
        for(int i=1;i<=maxDay;i++){
            boolean isWork = true;
            calendar.set(Calendar.DATE,i);
            day = calendar.get(Calendar.DATE);
            xiqi = calendar.get(Calendar.DAY_OF_WEEK);
            mon = calendar.get(Calendar.MONTH)+1;
            if(xiqi == 1 || xiqi == 7){
                isWork = false;
            }
            time = (mon<10?("0"+mon):mon)+""+(day<10?("0"+day):day);
            if(null != json.get(time)){
                if("0".equals(String.valueOf(json.get(time)))){
                    isWork = true;
                }else{
                    isWork = false;
                }
            }
            if(isWork){
                gongzuo.add(day);
            }else{
                xiuxi.add(day);
            }
        }
        map.put("date",date);
        map.put("isWork",gongzuo.contains(today));
        map.put("isLastWork",today==gongzuo.get(gongzuo.size()-1));
        map.put("gzr",Arrays.toString(gongzuo.toArray()));
        map.put("gzn",gongzuo.size());
        return map;
    }
	
	
	public static String getDateStr(){
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyyMMddHHmmssSSS");
		return sdf.format(new Date())+"-"+(int)(Math.random()*1000);
	}
	public static String getDateStryyyyMMddHHmm(){
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyyMMddHHmm");
		return sdf.format(new Date());
	}
	public static long getDateFromyyyyMMddHHmm(String str){
		if(str==null || str.length()!=12){
			return -1;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		try {
			return sdf.parse(str).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}	
	public static String getDateByFormat(String format){
		SimpleDateFormat sdf;
		try{
			sdf =   new SimpleDateFormat(format);
			return sdf.format(new Date());	
		}catch(Exception e){
			
		}
		sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(new Date());
	}
	public static int getWorkNum(){
		
		return 0;
	}
	
	
	public static long getNowTimeLong(){
		return System.currentTimeMillis();
	}
	
	public static void main(String[] args) {
		System.out.println(getDateStr());
		System.out.println(getDateStryyyyMMddHHmm());
		System.out.println(System.currentTimeMillis());
		System.out.println(getDateFromyyyyMMddHHmm("201610261803"));
        System.out.println(getDayInfo());
        System.out.println(Integer.parseInt("04"));

	}
}

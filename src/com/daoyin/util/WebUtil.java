package com.daoyin.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class WebUtil {
	
	public static final String pattern_ymd = "yyyy-MM-dd"; // pattern_ymd
	public static final String pattern_ymd_hms = "yyyy-MM-dd HH:mm:ss"; // pattern_ymdtime
	public static final String pattern_ymd_hms_s = "yyyy-MM-dd HH:mm:ss:SSS"; // pattern_ymdtimeMillisecond
	public static final String pattern_ymdhmss = "yyyyMMddHHmmssSSS";
	public static final String pattern_y="yyyy";
	public static final String pattern_ym="yyyy-MM";
	
	public static int[] getParms(String ids){
		String[] parms =  ids.split(",");
		int[] idss =new int[parms.length];
		for(int i = 0;i<parms.length;i++){
			idss[0] = Integer.parseInt(parms[0]); 
		}
		return idss;
	}

	public static Map<String,Object> getMap(String[] keys,Object[] values) {
		if (keys != null && values != null && keys.length == values.length) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0, len = keys.length; i < len; i++) {
				map.put(keys[i], values[i]);
			}
			return map;
		}
		return null;
	}
	
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getDate(){
		return new Date();
	}
	
	public static String date(){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern_ymd);
		return sdf.format(getDate());
	}
	
	/**
	 * 格式化
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		DateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	/**
	 * 上一个月的日期
	 * @return
	 */
	public static String format(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH,-1);
		return format(c.getTime(),pattern_ym);
	}
	
	/**
	 * 比较传的时间与当前时间比较并返回值
	 * @param date
	 * @return
	 */
	public static String format(String date){
		Boolean logic  = lgOrTg(date,format(getDate(), pattern_ym));
		if(logic){
			return date;
		}else{
			return format();
		}
	}
	
	/**
	 * 产生一个随机不重复的8位数
	 * @return
	 */
	public static  String  getWashedCode(){
		Long str = (WebUtil.getDate().getTime());
		String time = str.toString();
		String code = time.substring(time.length()-7);
		String washCode = code +(new Random().nextInt(10));
		return washCode;
	}
	
	/**
	 * 将时间字符串转换为16进制
	 * @param s
	 * @return
	 */
	  public static String toHexString() {  
	      return  Long.toHexString(getDate().getTime());
	    }  

		
	/**
	 * 删除本地文件
	 * Qing W
	 */
	public static boolean deleteFile(String url){
		File file = new File(url);
		 return file.delete();
	}
	/**
	 * 比较两个时间
	 * @param data1
	 * @param date2
	 * @return
	 */
	public static boolean  lgortg(String data1,String date2){
		SimpleDateFormat df = new SimpleDateFormat(pattern_ymd);
	        try {
	            Date dt1 = df.parse(data1);
	            Date dt2 = df.parse(date2);
	            if (dt1.getTime() > dt2.getTime()) {
	                return false;
	            }
	            return true;
	        }catch (Exception e) {
			}
			return false; 
	}
	
	/**
	 * 比较两个时间
	 * @param data1
	 * @param date2
	 * @return
	 */
	public static boolean  lgOrTg(String data1,String date2){
		SimpleDateFormat df = new SimpleDateFormat(pattern_ym);
	        try {
	            Date dt1 = df.parse(data1);
	            Date dt2 = df.parse(date2);
	            if (dt1.getTime() >= dt2.getTime()) {
	                return false;
	            }
	            return true;
	        }catch (Exception e) {
			}
			return false; 
	}
	
	
	/**
	 * 时间是否大于指定时间
	 * @param time
	 * @return
	 */
	public static boolean lgOrTg(String time,Integer result){
		SimpleDateFormat df = new SimpleDateFormat(pattern_ymd_hms);
		try {
			Date createDateTime = df.parse(time);
			Date nowDate = new Date();
			if((nowDate.getTime()-createDateTime.getTime())>result){
				return true;
			}else{
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
	}
	
	//日期转换
	public static Boolean IsDate(String dateTime){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern_ymd);
		try {
			sdf.parse(dateTime);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 分页排序
	 * Qing W
	 */												
	public static LinkedList<Integer> count(Integer pageCount,Integer index){//  6    1
		LinkedList<Integer> count = new LinkedList<Integer>();
		
		if(pageCount <5){
			for(int i=1;i<=pageCount;i++){
				count.add(i);
			}
		}else if(index+1 < pageCount){
			if(index <= 1){
				count.add(index);
				count.add(index+1);
				count.add(index+2);
				count.add(index+3);
			}else{
				count.add(index-1);
				count.add(index);
				count.add(index+1);
				count.add(index+2);
			}
		}else{
			if(index >= pageCount){
				count.add(index-3);
				count.add(index-2);
				count.add(index-1);
				count.add(index);
			}else{
				count.add(index-2);
				count.add(index-1);
				count.add(index);
				count.add(index+1);
			}
		}
		return count;
	} 
	
	/**
	 * 页码
	 * @param pages
	 * @return
	 */
	public static Integer getPage(String pages){
		Integer page=0;
			try{
			page = Integer.parseInt(pages);
			}catch (Exception e) {
				page=1;
			}
		return page;
	}
	
	
	/**
	 * 页码
	 * @param pages
	 * @return
	 */
	public static Integer getRows(String rows){
		Integer row=0;
			try{
				row = Integer.parseInt(rows);
			}catch (Exception e) {
				row=20;
			}
		return row;
	}
	
		
	public static String[] createThum(FileInputStream file,String allpath,String context){
		BufferedImage img;
		String imgname=new Date().getTime()+".jpg";
		try {
			img = ImageIO.read(file);
		    BufferedImage image = new BufferedImage(100, 100,BufferedImage.TYPE_INT_RGB );   
		    BufferedImage imagelarge = new BufferedImage(300, 300,BufferedImage.TYPE_INT_RGB );   
	        image.getGraphics().drawImage(img, 0, 0, 100, 100, null); 
	        imagelarge.getGraphics().drawImage(img, 0, 0, 300, 300, null); 
	        File destFile = new File(context+"/common/upload/thumb/"+imgname);  
	        File destFilelarge = new File(context+"/common/upload/large/"+imgname);  
	        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流  
	        FileOutputStream outlarge = new FileOutputStream(destFilelarge); // 输出到文件流  
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
	        JPEGImageEncoder encoderlarge = JPEGCodec.createJPEGEncoder(outlarge);  
	        encoder.encode(image); // JPEG编码  
	        encoderlarge.encode(imagelarge); // JPEG编码  
	        out.close();  
	        outlarge.close();  
	        return new String[]{allpath+"thumb/"+imgname,allpath+"large/"+imgname};
		} catch (IOException e) {
			e.printStackTrace();
		}      // 构造Image对象  
       
		return null;
	}

	/**
	 * @return
	 */
	public static String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern_ymd_hms);
		return sdf.format(getDate());
	}
	
	/**
	 * 格式化
	 * @param date
	 * @param parsePattern
	 * @param returnPattern
	 * @return
	 */
	public static String format(String date, String parsePattern, String returnPattern) {
		return format(parse(date, parsePattern), returnPattern);
	}
	
	/**
	 * 解析
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date parse(String date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(date);
		} catch (ParseException e) {
			System.out.println("ToolDateTime.parse异常：date值" + date + "，pattern值" + pattern);
			return null;
		}
	}
	
	/**
	 * 
	 * @param day 
	 * @return
	 */
	public static String anyDay(Integer day){
		Date date = WebUtil.getDate();
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(Calendar.DATE,day);//把日期往后增加一天.整数往后推,负数往前移动
		 date = calendar.getTime(); //这个时间就是日期往后推一天的结果
		 String tomorrow = WebUtil.format(date, WebUtil.pattern_ymd);
		 return tomorrow;
				 
	}
	
	
	public static String imgResizeByWidth641_286(InputStream in,String allpath,String context){
		BufferedImage img;
		String imgname=new Date().getTime()+".jpg";
		try {
			img = ImageIO.read(in);
			BufferedImage image = new BufferedImage(641, 286,BufferedImage.TYPE_INT_RGB );
			image.getGraphics().drawImage(img, 0, 0, 641, 286, null); 
			File destFile = new File(context+"/common/adv/upload/"+imgname);  
			FileOutputStream out = new FileOutputStream(destFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
			encoder.encode(image); // JPEG编码  
		    out.close();  
		    return allpath+imgname;
		}catch (Exception e) {
			return null;
		}
	}
	public static String imgResizeByWidth(InputStream in,String allpath,String context){
		BufferedImage img;
		String imgname=new Date().getTime()+".jpg";
		try {
			img = ImageIO.read(in);
			Integer width = img.getWidth();    // 得到源图宽  
			Integer height = img.getHeight();
			int h = (int) (height * 641 / width);  
			BufferedImage image = new BufferedImage(641, h,BufferedImage.TYPE_INT_RGB );
			image.getGraphics().drawImage(img, 0, 0, 641, h, null); 
			File destFile = new File(context+"/common/adv/upload/"+imgname);  
			FileOutputStream out = new FileOutputStream(destFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
			encoder.encode(image); // JPEG编码  
		    out.close();  
		    return allpath+imgname;
		}catch (Exception e) {
			return null;
		}
	}
	

	/**
	  * 得到某年某月的第一天
	  * @author dandy
	  * @param year
	  * @param month
	  * @return
	  */
	 public static String getFirstDayOfMonth(int year, int month) {
	 
	  Calendar cal = Calendar.getInstance();
	 
	  cal.set(Calendar.YEAR, year);
	 
	  cal.set(Calendar.MONTH, month-1);
	 
	  cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
	 
	 
	  return new SimpleDateFormat(WebUtil.pattern_ymd).format(cal.getTime());
	 }

	 /**
	  * 得到某年某月的最后一天
	  * @author dandy
	  * @param year
	  * @param month
	  * @return
	  */
	 public static String getLastDayOfMonth(int year, int month) {
	 
	  Calendar cal = Calendar.getInstance();
	 
	  cal.set(Calendar.YEAR, year);
	 
	  cal.set(Calendar.MONTH, month-1);
	 
	   cal.set(Calendar.DAY_OF_MONTH, 1);
	  int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	  cal.set(Calendar.DAY_OF_MONTH, value);
	 
	  return new SimpleDateFormat(WebUtil.pattern_ymd).format(cal.getTime());
	 
	 }
	 
	 /**
	  * @author dandy
	  * @param year
	  * @return
	  * 获得 2012 年 每月 的 1月1日 2月2日
	  */
	 public static String[] getYearAllMonthFirstDays(int year) {
		 String[] days = new String[12];
		 for (int i = 1; i < 13; i++) {
				days[i-1] = WebUtil.getFirstDayOfMonth(year, i);
			}
		 
		 return days;
	 }
	 
	 /**
	  * @author dandy
	  * @param year
	  * @return
	  * 获得 2012年 每月的 1月31日 12月31日
	  */
	 public static String[] getYearAllMonthLasttDays(int year) {
		 String[] days = new String[12];
		 for (int i = 1; i < 13; i++) {
				days[i-1] = WebUtil.getLastDayOfMonth(year, i);
			}
		 
		 return days;
	 }
    public static Boolean getFile(String type){
			Boolean logic = false;
			String[] suffix = new String[]{"jpg","png"};
			for (int i = 0; i < suffix.length; i++) {
				if(type.equals(suffix[i])){
					logic = true;
					break;
				}
			}
			return logic;
		}
    
	/**
	 * 获取随机 n 位字符串
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 } 

	
	
}

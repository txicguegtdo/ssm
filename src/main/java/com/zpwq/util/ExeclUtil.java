package com.zpwq.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ZPWQ on 2016/5/3.
 */
public class ExeclUtil {
    public ExeclUtil(){

    }
    private HSSFWorkbook workbook;
    public ExeclUtil(String file){
        InputStream in = null;
        try{
            in = new FileInputStream(file);
            workbook = new HSSFWorkbook(in);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(in != null){
                    in.close();
                }
            }catch(Exception e){

            }
        }
    }
    public void modExecl(String book,int row,int col,Object value){
        HSSFSheet sheet = workbook.getSheet(book);
        HSSFRow rows = sheet.getRow(row);
        HSSFCell cell = rows.getCell(col);
        System.out.print("原内容为:"+getStringCellValue(cell));
        setCellVlaul(cell,value);
        System.out.println(";修改后:"+getStringCellValue(cell));
    }
    public int getRowNum(){
    	if(workbook == null || workbook.getSheetAt(0)==null){
    		return 0;
    	}
    	return workbook.getSheetAt(0).getLastRowNum();
    }
    public int getColNum(){
    	if(workbook == null || workbook.getSheetAt(0) == null){
    		return 0;
    	}
    	return workbook.getSheetAt(0).getColumnWidth(getRowNum());
    }
    public List<HSSFCell> getCells(){
    	List<HSSFCell> list = new ArrayList<HSSFCell>();
    	HSSFSheet sheet = workbook.getSheetAt(0);
    	if(sheet == null){
    		return list;
    	}
    	int row = getRowNum();
    	for(int i=0;i<= row ; i++){
    		 HSSFRow rows = sheet.getRow(i);
    		 int col = rows.getLastCellNum();
    		 for(int j = 0;j< col;j++){
    			 HSSFCell cell = rows.getCell(j);
    			 if(!"".equals(getStringCellValue(cell))){
    				 list.add(cell);
    			 }
    		 }    		
    	}
    	return list;
    }
    
    
    public void saveExecl(String file){
    	
        OutputStream out = null;
        try{
        	String path = file.substring(0,file.lastIndexOf("/"));
        	File p = new File(path);
        	p.mkdirs();
        	File f = new File(file);
        	if(!f.exists()){
        		f.createNewFile();
        	}
            out = new FileOutputStream(f);
            workbook.write(out);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(out != null){
                    out.close();
                }
            }catch(Exception e){
            }
        }
    }
    public static boolean delExecl(String file){
        File f = new File(file);
        if (f.exists() && f.isFile()) {
            return f.delete();
        }
        return false;
    }
    public static void setCellVlaul(HSSFCell cell,Object value){
        if(value==null){
            cell.setCellValue("");
        }else if(value instanceof String){
            cell.setCellValue((String)value);
        }else if(value instanceof Integer){
            cell.setCellValue((Integer)value);
        }else if(value instanceof Double){
            cell.setCellValue((Double)value);
        }else if(value instanceof Boolean){
            cell.setCellValue((Boolean)value);
        }else if(value instanceof Date){
            cell.setCellValue((Date)value);
        }else if(value instanceof Calendar){
            cell.setCellValue((Calendar)value);
        }else{
            cell.setCellValue(value.toString());
        }
    }
    public static String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        return strCell;
    }

    public static void main(String[] args) {
    	String str = "#{name}-1212";
    	String rep = "#{name}";
    	System.out.println(str.replaceAll(rep.replace("{", "\\{").replace("}", "\\}"), "李四"));
    	
    	
        String fileToBeRead = "C:/Users/zpwq/Documents/临时文件/XX月考勤-张三.xls"; // excel位置
        ExeclUtil execl = new ExeclUtil(fileToBeRead);
        List<HSSFCell> list = execl.getCells();
        for(HSSFCell cell : list){
        	System.out.println(getStringCellValue(cell));
        }

        
        
        
        
        //execl.saveExecl(fileToBeRead);
    }
}

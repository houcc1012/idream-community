package com.idream.commons.lib.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author hejiang
 */
public class ExcelReaderUtils {

    private static Logger logger = LoggerFactory.getLogger(ExcelReaderUtils.class);


    /**
     * @Author: hejiang
     * @Description: 读取 2007 xls 内容 转换成对应java对象
     * @Date: 10:19 2018/4/4
     */
    public static <T> List<T> readXlsContent(File file, String[] title, Class<T> clazz) throws OfficeXmlFileException, IOException, ParseException {
        try {
            Workbook hfw = null;
            try {
                //2010 xlsx文件则读取
                hfw = new XSSFWorkbook(new FileInputStream(file));
            } catch (OfficeXmlFileException e) {
                logger.info("换格式读取Excel文件");
                //2007xls文件则读取
                hfw = new HSSFWorkbook(new FileInputStream(file));
            }
            Sheet hfs = hfw.getSheetAt(0);
            //获取标题头
            Row hfr = hfs.getRow(1);
            //获取总列数
            int colNum = hfr.getPhysicalNumberOfCells();
            //获取总行数
            int rowNum = hfs.getLastRowNum();
            //遍历读取表格内容
            List<T> data = Lists.newArrayList();
            for (int i = 2; i <= rowNum; i++) {
                JSONObject jsonObject = new JSONObject();
                hfr = hfs.getRow(i);
                if (hfr == null) {
                    logger.info("第" + (i + 1) + "行数据为空");
                    continue;
                }
                for (int j = 0; j < colNum && j < title.length; j++) {
                    String value = getCellValue(hfr.getCell(j));
                    if (StringUtils.isNotEmpty(value)) {
                        jsonObject.put(title[j], value);
                    }
                }
                jsonObject.put("rowsNumber", i + 1);
                //json转对象
                T t = JSON.parseObject(JSON.toJSONString(jsonObject), clazz);
                data.add(t);
            }
            return data;
        } catch (Exception e) {
            logger.error("读取xls文件失败", e);
        }
        return null;
    }

//    /**
//     * @Author: hejiang
//     * @Description: 读取 2010 xlsx 内容 转换成对应java对象
//     * @Date: 11:13 2018/4/4
//     */
//    public <T> List<T> readXlsxContent(File file, String[] title, Class<T> clazz){
//        List<T> data = Lists.newArrayList();
//        InputStream is = null;
//        try {
//            is = new FileInputStream(file);
//            XSSFWorkbook xfw = new XSSFWorkbook(is);
//            XSSFSheet xfs = xfw.getSheetAt(0);
//            //获取标题头
//            XSSFRow xfr = xfs.getRow(1);
//            //获取总列数
//            int colNum = xfr.getPhysicalNumberOfCells();
//            //获取总行数
//            int rowNum = xfs.getLastRowNum();
//            for(int i = 2 ; i <= rowNum ; i++){
//                JSONObject jsonObject = new JSONObject();
//                xfr = xfs.getRow(i);
//                if(xfr == null){
//                    logger.info("第" + (i+1) + "行数据为空");
//                    continue;
//                }
//                for(int j = 0 ; j < colNum && j < title.length ; j++){
//                    String value = getCellValue(xfr.getCell(j));
//                    if(StringUtils.isNotEmpty(value)){
//                        jsonObject.put(title[j], value);
//                    }
//                }
//                jsonObject.put("rowsNumber", i + 1);
//                //json转对象
//                T t = JSON.parseObject(JSON.toJSONString(jsonObject), clazz);
//                data.add(t);
//            }
//            return data;
//        }catch (Exception e) {
//            logger.error("文件读取失败", e);
//        }finally {
//            IOUtils.closeQuietly(is);
//        }
//        return null;
//    }


    /**
     * @Author: hejiang
     * @Description: 获取单元格内容
     * @Date: 11:15 2018/4/4
     */
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: {//数字
                if (DateUtil.isCellDateFormatted(cell)) {//日期
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    cellValue = format.format(cell.getDateCellValue());
                } else {//如果是纯数字
                    DecimalFormat dfs = new DecimalFormat("0");

                    cellValue = dfs.format(cell.getNumericCellValue()) + "";
                }
                break;
            }
            case Cell.CELL_TYPE_FORMULA: { //表达式类型
                //判断当前cell的值是否为Date
                cellValue = cell.getCellFormula();
                break;
            }
            case Cell.CELL_TYPE_STRING://字符串
                cellValue = cell.getRichStringCellValue().getString().trim();
                break;
            case Cell.CELL_TYPE_BOOLEAN://boolean
                cellValue = cell.getBooleanCellValue() ? "1" : "0";
                break;
        }
        return cellValue;
    }
}

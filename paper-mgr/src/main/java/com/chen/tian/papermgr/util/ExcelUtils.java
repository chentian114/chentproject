package com.chen.tian.papermgr.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.Iterator;

/**
 * Created by ChenTian on 2018/7/4.
 */
public class ExcelUtils {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
    public static final String EXCEL_XLSX = "xlsx";
    public static final String EXCEL_XLS = "xls";

    /**
     * 拷贝文件
     * @param toPath
     * @param fromPath
     */
    public static void copyFile(String toPath,String fromPath) throws Exception{
        InputStream fromIs = new FileInputStream(fromPath);
        OutputStream toOs = new FileOutputStream(toPath);
        FileCopyUtils.copy(fromIs,toOs);
    }

    /**
     * 读取并打印excel内容
     * @param path
     */
    public static void readExcel2013(String path) {
        try {
            checkExcel2013Vaild(new File(path));

            XSSFWorkbook xwb = new XSSFWorkbook(path);
            for (int numSheet = 0; numSheet < xwb.getNumberOfSheets(); numSheet++) {
                XSSFSheet xSheet = xwb.getSheetAt(numSheet);
                if (xSheet == null) {
                    continue;
                }
                for (int numRow = 0; numRow <= xSheet.getLastRowNum(); numRow++) {
                    XSSFRow xRow = xSheet.getRow(numRow);
                    if (xRow == null) {
                        continue;
                    }
                    for (int numCell = 0; numCell <= xRow.getLastCellNum(); numCell++) {
                        XSSFCell xCell = xRow.getCell(numCell);
                        if (xCell == null) {
                            continue;
                        }
                        String value = getCellValue(xCell);
                        logger.info("excel numSheet:{} numRow:{} numCell:{} value:{}", numSheet, numRow, numCell, value);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("readExcel path:{} error:", path, e);
        }
    }

    /**
     * 获取单元格内容并转换成String
     * @param xCell
     * @return
     */
    private static String getCellValue(XSSFCell xCell) {
        if(xCell.getCellTypeEnum() == CellType.BOOLEAN){
            return String.valueOf(xCell.getBooleanCellValue());
        }else if(xCell.getCellTypeEnum() == CellType.NUMERIC){
            return String.valueOf(xCell.getNumericCellValue());
        }else{
            return String.valueOf(xCell.getStringCellValue());
        }
    }


    public static boolean isExcel2013(String fileName){
        if (fileName.endsWith(EXCEL_XLSX)) {
            return true;
        }
        return false;
    }
    public static boolean isExcel2003(String fileName){
        if (fileName.endsWith(EXCEL_XLS)) {
            return true;
        }
        return false;
    }
    /**
     * 校验文件是否为excel2013以上版本
     * @param file
     * @throws Exception
     */
    public static void checkExcel2013Vaild(File file) throws Exception {
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        if (!(file.isFile() &&  file.getName().endsWith(EXCEL_XLSX))) {
            throw new Exception("文件不是Excel2013");
        }
    }

    /**
     * 校验文件是否为excel2013以上版本
     * @param file
     * @throws Exception
     */
    public static void checkExcel2003Vaild(File file) throws Exception {
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        if (!(file.isFile() &&  file.getName().endsWith(EXCEL_XLS))) {
            throw new Exception("文件不是Excel2003");
        }
    }

    public static void copyExcel(String fromExcelPath,String toExcelPath){
        try {
            XSSFWorkbook toXwb= copyWorkbook2013(fromExcelPath, toExcelPath);
            toXwb.write(new FileOutputStream(toExcelPath));
        }catch (Exception e){
            logger.error("copyExcel fromExcelPath:{} toExcelPath:{} exception:",fromExcelPath,toExcelPath,e);
        }
    }


    public static XSSFWorkbook copyWorkbook2013(String fromExcelPath,String toExcelPath){
        try {
            checkExcel2013Vaild(new File(fromExcelPath));
            isExistAndCreatePath(toExcelPath);

            XSSFWorkbook xwb = new XSSFWorkbook(fromExcelPath);
            XSSFWorkbook toXwb = new XSSFWorkbook();
            for(Iterator sheetIt =xwb.sheetIterator(); sheetIt.hasNext();){
                XSSFSheet fromSheet = (XSSFSheet)sheetIt.next();
                XSSFSheet toSheet = toXwb.createSheet(fromSheet.getSheetName());
                copySheet(toXwb,fromSheet,toSheet);
            }
            return xwb;
        }catch (Exception e){
            logger.error("copyWorkbook fromExcelPath:{} toExcelPath:{} exception:",fromExcelPath,toExcelPath,e);
        }
        return null;
    }



    public static void isExistAndCreatePath(String toExcelPath) throws IOException {
        File toFile = new File(toExcelPath);
        synchronized (ExcelUtils.class) {
            if (!toFile.exists()) {
                File fileDir = toFile.getParentFile();
                if(!fileDir.exists()){
                    fileDir.mkdirs();
                }
                toFile.createNewFile();
            }
        }
    }

    /**
     * 复制fromSheet内容到xwb中的toSheet中
     * @param toSheet
     * @param fromSheet
     */
    public static void copySheet(XSSFWorkbook toXwb,XSSFSheet fromSheet,XSSFSheet toSheet){
        mergerRegion(fromSheet,toSheet);
        for(Iterator rowIt = fromSheet.rowIterator() ; rowIt.hasNext();){
            XSSFRow fromRow = (XSSFRow) rowIt.next();
            XSSFRow toRow = toSheet.createRow(fromRow.getRowNum());
            copyRow(toXwb, fromRow, toRow);
        }
    }



    /**
     * 合并单元格区域处理
     * @param fromSheet
     * @param toSheet
     */
    private static void mergerRegion(XSSFSheet fromSheet, XSSFSheet toSheet) {
        int sheetMergerCount = fromSheet.getNumMergedRegions();
        for(int i=0; i< sheetMergerCount; i++){
            CellRangeAddress mergedRegion = fromSheet.getMergedRegion(i);
            toSheet.addMergedRegion(mergedRegion);
        }
    }


    /**
     * Row行复制
     * @param toXwb
     * @param toRow
     * @param fromRow
     */
    public static void copyRow(XSSFWorkbook toXwb,XSSFRow fromRow,XSSFRow toRow) {
        for(Iterator cellIt = fromRow.cellIterator(); cellIt.hasNext();){
            XSSFCell fromCell = (XSSFCell)cellIt.next();
            XSSFCell toCell = toRow.createCell(fromCell.getColumnIndex());
            copyCell(toXwb, fromCell, toCell);
        }
    }


    /**
     * Cell单元格复制
     * @param toCell
     * @param fromCell
     */
    public static void copyCell(XSSFWorkbook toXwb,XSSFCell fromCell,XSSFCell toCell) {
        XSSFCellStyle newStyle = toXwb.createCellStyle();
        XSSFFont newFont = toXwb.createFont();
        copyCellStyle(fromCell.getCellStyle(), newStyle, newFont);
        toCell.setCellStyle(newStyle);


        if(fromCell.getCellComment() != null){
            toCell.setCellComment(fromCell.getCellComment());
        }
        CellType fromCellType = fromCell.getCellTypeEnum();
        toCell.setCellType(fromCellType);
        if(fromCellType == CellType.NUMERIC){
            toCell.setCellValue(fromCell.getNumericCellValue());
        }else if(fromCellType == CellType.BOOLEAN){
            toCell.setCellValue(fromCell.getBooleanCellValue());
        }else{
            toCell.setCellType(CellType.STRING);
            toCell.setCellValue(String.valueOf(fromCell.getStringCellValue()));
        }

    }


    private static void copyCellStyle(XSSFCellStyle fromStyle, XSSFCellStyle toStyle ,XSSFFont toFont) {
        toStyle.setAlignment(fromStyle.getAlignmentEnum());
        //边框和边框颜色
        toStyle.setBorderBottom(fromStyle.getBorderBottomEnum());
        toStyle.setBorderLeft(fromStyle.getBorderLeftEnum());
        toStyle.setBorderRight(fromStyle.getBorderRightEnum());
        toStyle.setBorderTop(fromStyle.getBorderTopEnum());
        toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
        toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
        toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());
        toStyle.setRightBorderColor(fromStyle.getRightBorderColor());

        //背景和前景
        toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
        toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());

        toStyle.setDataFormat(fromStyle.getDataFormat());
        toStyle.setFillPattern(fromStyle.getFillPatternEnum());
        toStyle.setHidden(fromStyle.getHidden());
        toStyle.setIndention(fromStyle.getIndention());
        toStyle.setLocked(fromStyle.getLocked());
        toStyle.setRotation(fromStyle.getRotation());
        toStyle.setVerticalAlignment(fromStyle.getVerticalAlignmentEnum());
        toStyle.setWrapText(fromStyle.getWrapText());

        //字体
        XSSFFont fromFont = fromStyle.getFont();
        toFont.setFontHeightInPoints(fromFont.getFontHeightInPoints()); //设置字体大小
        toFont.setColor(fromFont.getColor());
        toFont.setFontName(fromFont.getFontName());
        toFont.setBold(fromFont.getBold());

        toStyle.setFont(toFont);
    }

    public static void removeMergedRegion(HSSFSheet sheet,int row ,int column)
    {
        int sheetMergeCount = sheet.getNumMergedRegions();//获取所有的单元格
        int index = 0;//用于保存要移除的那个单元格序号
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i); //获取第i个单元格
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            if(row >= firstRow && row <= lastRow)
            {
                if(column >= firstColumn && column <= lastColumn)
                {
                    index = i;
                }
            }
        }
        sheet.removeMergedRegion(index);//移除合并单元格
    }



}

package com.chen.test;

import com.chen.tian.papermgr.util.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.*;

/**
 * Created by ChenTian on 2018/7/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
public class TestExcel {

    @Value("${excelTemplateFile}")
    private String excelTemplateFile;

    @Test
    public void testCheck(){
        String fileName="D:\\tmpmg\\送货单.xlsx";
        System.out.println(ExcelUtils.isExcel2013(fileName));
        System.out.println(ExcelUtils.isExcel2003(fileName));

    }
    @Test
    public void testConfig(){
        System.out.println(excelTemplateFile);
        String fromPath = excelTemplateFile;
        String toPath="D:\\tmpmg\\送货单.xls";
        try {
            ExcelUtils.isExistAndCreatePath(toPath);
            File file = ResourceUtils.getFile(fromPath);
            ExcelUtils.copyFile(toPath, file.getAbsolutePath());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void testExcel(){
        try {
            File file = ResourceUtils.getFile(excelTemplateFile);
            ExcelUtils.readExcel2013(file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExcelCopy(){
        try {
            String toPath="D:\\tmpm\\送货单.xlsx";
            File file = ResourceUtils.getFile("classpath:excel/template.xlsx");
            ExcelUtils.copyExcel(file.getAbsolutePath(),toPath);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadExcel(){
        try {
            String filePath = "classpath:excel/template2003.xls";

            System.out.println(filePath.substring(filePath.lastIndexOf(".")));
           // File cfgFile = ResourceUtils.getFile("classpath:excel/temp713.xls");
            File cfgFile2 = ResourceUtils.getFile(filePath);
           // System.out.println(cfgFile.exists());
          //  System.out.println(cfgFile.getAbsolutePath());
          // readExecl(cfgFile.getAbsolutePath());
             readExecl2003(cfgFile2.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //读取excel表格中的数据，path代表excel路径
    public void readExecl(String path) {
        try {
            //读取的时候可以使用流，也可以直接使用文件名
           // XSSFWorkbook xwb = new XSSFWorkbook(path);
            XSSFWorkbook xwb = new XSSFWorkbook(path);


            //循环工作表sheet
            for (int numSheet = 0; numSheet < xwb.getNumberOfSheets(); numSheet++) {
                XSSFSheet xSheet = xwb.getSheetAt(numSheet);

                if (xSheet == null) {
                    continue;
                }
                //循环行row
                for (int numRow = 0; numRow <= xSheet.getLastRowNum(); numRow++) {
                    XSSFRow xRow = xSheet.getRow(numRow);
                    if (xRow == null) {
                        continue;
                    }
                    //循环列cell
                    for (int numCell = 0; numCell <= xRow.getLastCellNum(); numCell++) {
                        XSSFCell xCell = xRow.getCell(numCell);
                        if (xCell == null) {
                            continue;
                        }
                        //System.out.println(numRow + ":" + numCell + xCell.getStringCellValue());
                        if(!StringUtils.isEmpty(getValue(xCell)))
                        //输出值
                        System.out.println(numRow + ":" + numCell + "excel表格中取出的:" + getValue(xCell));
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readExecl2003(String path) {
        try {
            //读取的时候可以使用流，也可以直接使用文件名
            HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(new File(path)));


            //循环工作表sheet
            for (int numSheet = 0; numSheet < hwb.getNumberOfSheets(); numSheet++) {
                HSSFSheet hSheet = hwb.getSheetAt(numSheet);

                if (hSheet == null) {
                    continue;
                }
                //循环行row
                for (int numRow = 0; numRow <= hSheet.getLastRowNum(); numRow++) {
                    HSSFRow hRow = hSheet.getRow(numRow);
                    if (hRow == null) {
                        continue;
                    }
                    //循环列cell
                    for (int numCell = 0; numCell <= hRow.getLastCellNum(); numCell++) {
                       HSSFCell hCell = hRow.getCell(numCell);
                        if (hCell == null) {
                            continue;
                        }
                        if(!StringUtils.isEmpty(getValue(hCell)))
                        //输出值
                         System.out.println(numRow + ":" + numCell + "excel表格中取出的:" + getValue(hCell));
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取出每列的值
     *
     * @param xCell 列
     * @return
     */
    private String getValue(XSSFCell xCell) {
        if(xCell.getCellTypeEnum() == CellType.BOOLEAN){
            return String.valueOf(xCell.getBooleanCellValue());
        }else if(xCell.getCellTypeEnum() == CellType.NUMERIC){
            return String.valueOf(xCell.getNumericCellValue());
        } else {
            return String.valueOf(xCell.getStringCellValue());
        }
    }

    private String getValue(HSSFCell xCell) {
        if(xCell.getCellTypeEnum() == CellType.BOOLEAN){
            return String.valueOf(xCell.getBooleanCellValue());
        }else if(xCell.getCellTypeEnum() == CellType.NUMERIC){
            return String.valueOf(xCell.getNumericCellValue());
        } else {
            return String.valueOf(xCell.getStringCellValue());
        }
    }


    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static Workbook getWorkbok(InputStream in,File file) throws IOException{
        Workbook wb = null;
        if(file.getName().endsWith(EXCEL_XLS)){	 //Excel 2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){	// Excel 2007/2010
            wb = new XSSFWorkbook(file.getAbsolutePath());
        }
        return wb;
    }

}

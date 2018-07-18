package com.chen.tian.papermgr.service;

import com.chen.tian.papermgr.constant.Consts;
import com.chen.tian.papermgr.dto.OrderInfoEnum;
import com.chen.tian.papermgr.entity.TOrderEntity;
import com.chen.tian.papermgr.entity.TOrderProdEntity;
import com.chen.tian.papermgr.util.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

/**
 * 处理excel
 * Created by ChenTian on 2018/7/4.
 */
@Service
public class ExcelService {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
    private final static int ORDER_EXCEL_PRODS_DEFAULT_MAX_SIZE = 6;//默认最大货单行数


    @Value("${excelPath}")
    private String excelPath;
    @Value("${excelTemplateFile}")
    private String excelTemplateFile;

    public void buildExcelByOrder(TOrderEntity orderEntity) throws Exception{
        long start = System.currentTimeMillis();
        File fromFile = ResourceUtils.getFile(excelTemplateFile);
        String fromFilePath = fromFile.getAbsolutePath();
        String toFilePath = buildToFilePath(orderEntity);
        ExcelUtils.isExistAndCreatePath(toFilePath);
        logger.info("fromFilePath:{} toFilePath:{}", fromFilePath, toFilePath);
        if(ExcelUtils.isExcel2013(fromFilePath)) {
            XSSFWorkbook xwb = ExcelUtils.copyWorkbook2013(fromFilePath, toFilePath);

            fillOrderInfoToExcel(xwb, orderEntity);

            if (xwb != null) {
                xwb.write(new FileOutputStream(toFilePath));
            }
        }else if(ExcelUtils.isExcel2003(fromFilePath)){
            ExcelUtils.copyFile(toFilePath, fromFilePath);
            HSSFWorkbook hwb = fillOrderInfoToExcel2003(toFilePath, orderEntity);
            if (hwb != null) {
                hwb.write(new FileOutputStream(toFilePath));
            }
        }else {
            logger.error("fromFilePath:{} toFilePath:{} fromFilePath error!", fromFilePath, toFilePath);
        }
        long end = System.currentTimeMillis();
        logger.info("buildExcelByOrder sucess time cost:{}ms",(end-start));
    }


    private HSSFWorkbook fillOrderInfoToExcel2003(String toFilePath,TOrderEntity orderEntity) throws Exception{
        File file = new File(toFilePath);
        ExcelUtils.checkExcel2003Vaild(file);
        HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(toFilePath));

        HSSFSheet hSheet = hwb.getSheetAt(0);
        String custInfo = orderEntity.getCustomerEntity().getCustName()+"/"+orderEntity.getCustomerPhone();
        HSSFRow custRow = hSheet.getRow(OrderInfoEnum.CUSTOMER_INFO_2003.getRowNum());
        HSSFCell custCell = custRow.getCell(OrderInfoEnum.CUSTOMER_INFO_2003.getCellNum());
        custCell.setCellValue(custInfo);

        HSSFRow orderRow = hSheet.getRow(OrderInfoEnum.ORDER_NUMBER_2003.getRowNum());
        HSSFCell orderCell = orderRow.getCell(OrderInfoEnum.ORDER_NUMBER_2003.getCellNum());
        orderCell.setCellValue(orderEntity.getOrderNumber());

        HSSFRow addressRow = hSheet.getRow(OrderInfoEnum.DELIVERY_ADDRESS_2003.getRowNum());
        HSSFCell addressCell = addressRow.getCell(OrderInfoEnum.DELIVERY_ADDRESS_2003.getCellNum());
        addressCell.setCellValue(orderEntity.getDeliveryAddress());

        HSSFRow dateRow = hSheet.getRow(OrderInfoEnum.DELIVER_DATE_2003.getRowNum());
        HSSFCell dateCell = dateRow.getCell(OrderInfoEnum.DELIVER_DATE_2003.getCellNum());
        dateCell.setCellValue(orderEntity.getDeliverDate());

        int prodRowNum = OrderInfoEnum.ORDER_PRODS_START_2003.getRowNum();
        int addProdCount = 0;
        for(TOrderProdEntity orderProd : orderEntity.getOrderProductList()){
            addProdCount++;
            if(addProdCount > ORDER_EXCEL_PRODS_DEFAULT_MAX_SIZE){
                break;
            }
            HSSFRow prodRow = hSheet.getRow(prodRowNum);

            HSSFCell prodNameCell = prodRow.getCell(Consts.PROD_NAME_CELL_NUM_2003);
            prodNameCell.setCellValue(orderProd.getProdName());

            HSSFCell gweightCell = prodRow.getCell(Consts.GWEIGHT_CELL_NUM_2003);
            gweightCell.setCellValue(orderProd.getGweight());

            HSSFCell specCell = prodRow.getCell(Consts.SPEC_CELL_NUM_2003);
            String finalSpec = converSpecBySpecTypeAndSpec(orderProd.getSpecType(), orderProd.getSpec());
            specCell.setCellValue(finalSpec);

            HSSFCell amountCell = prodRow.getCell(Consts.AMOUNT_CELL_NUM_2003);
            amountCell.setCellValue(orderProd.getAmount());

            HSSFCell unitCell = prodRow.getCell(Consts.UNIT_CELL_NUM_2003);
            unitCell.setCellValue(orderProd.getUnit());

            HSSFCell unitPriceCell = prodRow.getCell(Consts.UNIT_PRICE_CELL_NUM_2003);
            unitPriceCell.setCellValue(orderProd.getUnitPrice());

            HSSFCell priceCell = prodRow.getCell(Consts.PRICE_CELL_NUM_2003);
            priceCell.setCellValue(orderProd.getMoney());

            HSSFCell memoCell = prodRow.getCell(Consts.MEMO_CELL_NUM_2003);
            memoCell.setCellValue(orderProd.getMemo());

            prodRowNum++;
        }

        HSSFRow moneyUpperRow = hSheet.getRow(OrderInfoEnum.MONEY_COUNT_UPPER_2003.getRowNum());
        HSSFCell moneyUpperCell = moneyUpperRow.getCell(OrderInfoEnum.MONEY_COUNT_UPPER_2003.getCellNum());
        moneyUpperCell.setCellValue(orderEntity.getMoneyCountUpper());

        HSSFRow moneyRow = hSheet.getRow(OrderInfoEnum.MONEY_COUNT_2003.getRowNum());
        HSSFCell moneyCell = moneyRow.getCell(OrderInfoEnum.MONEY_COUNT_2003.getCellNum());
        moneyCell.setCellValue(String.valueOf(orderEntity.getMoneyCount().intValue()));

        HSSFRow paymentRow = hSheet.getRow(OrderInfoEnum.PAYMENT_TYPE_2003.getRowNum());
        HSSFCell paymentCell = paymentRow.getCell(OrderInfoEnum.PAYMENT_TYPE_2003.getCellNum());
        paymentCell.setCellValue("4.货款结算方式："+orderEntity.getPaymentType());

        HSSFRow userRow = hSheet.getRow(OrderInfoEnum.USER_2003.getRowNum());
        HSSFCell userCell = userRow.getCell(OrderInfoEnum.USER_2003.getCellNum());
        userCell.setCellValue("制单员："+orderEntity.getUser().getName());

        HSSFRow salesRow = hSheet.getRow(OrderInfoEnum.SALES_MAN_2003.getRowNum());
        HSSFCell salesCell = salesRow.getCell(OrderInfoEnum.SALES_MAN_2003.getCellNum());
        String finalSlaes = orderEntity.getSalesmanEntity().getSalesName() +"/" + orderEntity.getCustomerEntity().getType();
        salesCell.setCellValue("业务员:"+finalSlaes);

        HSSFRow deliverRow = hSheet.getRow(OrderInfoEnum.DELIVER_TYPE_2003.getRowNum());
        HSSFCell deliverCell = deliverRow.getCell(OrderInfoEnum.DELIVER_TYPE_2003.getCellNum());
        deliverCell.setCellValue("送货员："+orderEntity.getDeliverType());
        return hwb;
    }

    private String converSpecBySpecTypeAndSpec(String specType, String spec) {
        //规格进十位显示
        int scalBit = 10;
        String reg = "\\*";
        if(Consts.SPEC_TYPE_AREA.equals(specType)){
            String [] arrs = spec.split(reg);
            Float lenspec = Float.valueOf(arrs[0]);
            Float widspec = Float.valueOf(arrs[1]);
            lenspec = lenspec * scalBit;
            widspec = widspec * scalBit;
            return lenspec.intValue()+"*"+widspec.intValue();
        }else if(Consts.SPEC_TYPE_WIDE.equals(specType)){
            Float fspec = Float.valueOf(spec) * scalBit;
            return String.valueOf(fspec.intValue());
        }else {
            return spec;
        }
    }

    private void fillOrderInfoToExcel(XSSFWorkbook xwb,TOrderEntity orderEntity) {
        XSSFSheet xSheet = xwb.getSheetAt(0);

        String custInfo = orderEntity.getCustomerEntity().getCustName()+"/"+orderEntity.getCustomerPhone();
        XSSFRow custRow = xSheet.getRow(OrderInfoEnum.CUSTOMER_INFO.getRowNum());
        XSSFCell custCell = custRow.getCell(OrderInfoEnum.CUSTOMER_INFO.getCellNum());
        custCell.setCellValue(custInfo);

        XSSFRow orderRow = xSheet.getRow(OrderInfoEnum.ORDER_NUMBER.getRowNum());
        XSSFCell orderCell = orderRow.getCell(OrderInfoEnum.ORDER_NUMBER.getCellNum());
        orderCell.setCellValue(orderEntity.getOrderNumber());

        XSSFRow addressRow = xSheet.getRow(OrderInfoEnum.DELIVERY_ADDRESS.getRowNum());
        XSSFCell addressCell = addressRow.getCell(OrderInfoEnum.DELIVERY_ADDRESS.getCellNum());
        addressCell.setCellValue(orderEntity.getDeliveryAddress());

        XSSFRow dateRow = xSheet.getRow(OrderInfoEnum.DELIVER_DATE.getRowNum());
        XSSFCell dateCell = dateRow.getCell(OrderInfoEnum.DELIVER_DATE.getCellNum());
        dateCell.setCellValue(orderEntity.getDeliverDate());

        int prodRowNum = OrderInfoEnum.ORDER_PRODS_START.getRowNum();
        int addProdCount = 0;
        for(TOrderProdEntity orderProd : orderEntity.getOrderProductList()){
            addProdCount++;
            if(addProdCount > ORDER_EXCEL_PRODS_DEFAULT_MAX_SIZE){
                break;
            }
            XSSFRow prodRow = xSheet.getRow(prodRowNum);

            XSSFCell prodNameCell = prodRow.getCell(Consts.PROD_NAME_CELL_NUM);
            prodNameCell.setCellValue(orderProd.getProdName());

            XSSFCell gweightCell = prodRow.getCell(Consts.GWEIGHT_CELL_NUM);
            gweightCell.setCellValue(orderProd.getGweight());

            XSSFCell specCell = prodRow.getCell(Consts.SPEC_CELL_NUM);
            specCell.setCellValue(orderProd.getSpec());

            XSSFCell amountCell = prodRow.getCell(Consts.AMOUNT_CELL_NUM);
            amountCell.setCellValue(orderProd.getAmount());

            XSSFCell unitCell = prodRow.getCell(Consts.UNIT_CELL_NUM);
            unitCell.setCellValue(orderProd.getUnit());

            XSSFCell unitPriceCell = prodRow.getCell(Consts.UNIT_PRICE_CELL_NUM);
            unitPriceCell.setCellValue(orderProd.getUnitPrice());

            XSSFCell priceCell = prodRow.getCell(Consts.PRICE_CELL_NUM);
            priceCell.setCellValue(orderProd.getMoney());

            XSSFCell memoCell = prodRow.getCell(Consts.MEMO_CELL_NUM);
            memoCell.setCellValue(orderProd.getMemo());

            prodRowNum++;
        }

        XSSFRow moneyUpperRow = xSheet.getRow(OrderInfoEnum.MONEY_COUNT_UPPER.getRowNum());
        XSSFCell moneyUpperCell = moneyUpperRow.getCell(OrderInfoEnum.MONEY_COUNT_UPPER.getCellNum());
        moneyUpperCell.setCellValue(orderEntity.getMoneyCountUpper());

        XSSFRow moneyRow = xSheet.getRow(OrderInfoEnum.MONEY_COUNT.getRowNum());
        XSSFCell moneyCell = moneyRow.getCell(OrderInfoEnum.MONEY_COUNT.getCellNum());
        moneyCell.setCellValue(String.valueOf(orderEntity.getMoneyCount().intValue()));

        XSSFRow paymentRow = xSheet.getRow(OrderInfoEnum.PAYMENT_TYPE.getRowNum());
        XSSFCell paymentCell = paymentRow.getCell(OrderInfoEnum.PAYMENT_TYPE.getCellNum());
        paymentCell.setCellValue("4.货款结算方式："+orderEntity.getPaymentType());

        XSSFRow userRow = xSheet.getRow(OrderInfoEnum.USER.getRowNum());
        XSSFCell userCell = userRow.getCell(OrderInfoEnum.USER.getCellNum());
        userCell.setCellValue("制单员："+orderEntity.getUser().getName());

        XSSFRow salesRow = xSheet.getRow(OrderInfoEnum.SALES_MAN.getRowNum());
        XSSFCell salesCell = salesRow.getCell(OrderInfoEnum.SALES_MAN.getCellNum());
        String finalSlaes = orderEntity.getSalesmanEntity().getSalesName() +"/" + orderEntity.getCustomerEntity().getType();
        salesCell.setCellValue("业务员:"+finalSlaes);

        XSSFRow deliverRow = xSheet.getRow(OrderInfoEnum.DELIVER_TYPE.getRowNum());
        XSSFCell deliverCell = deliverRow.getCell(OrderInfoEnum.DELIVER_TYPE.getCellNum());
        deliverCell.setCellValue("送货员："+orderEntity.getDeliverType());
    }

    /**
     * 构建新生成货单存放路径
     * @param orderEntity
     * @return
     */
    public String buildToFilePath(TOrderEntity orderEntity){
        SimpleDateFormat sdf = new SimpleDateFormat(Consts.ORDER_EXCEL_DIR_YYYYMMDD);
        StringBuilder toPathBuild = new StringBuilder(excelPath);
        toPathBuild.append("//").append(orderEntity.getCustomerEntity().getCustName());
        toPathBuild.append("//").append(sdf.format(orderEntity.getCreateTime()));
        toPathBuild.append("//").append("送货单").append(orderEntity.getOrderNumber());
        toPathBuild.append(getExcelTemplateFileSuffix());
        return toPathBuild.toString();
    }

    /**
     * 获取模板excel尾缀
     * @return
     */
    public String getExcelTemplateFileSuffix(){
        return excelTemplateFile.substring(excelTemplateFile.lastIndexOf("."));
    }

}

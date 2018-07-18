package com.chen.tian.papermgr.constant;

/**
 * Created by ChenTian on 2018/5/9.
 */
public class Consts {
    /** 用户信息 */
    public static final String SESSION_KEY_LOGIN_USER_INFO = "loginUserInfo";
    /** 用户ID信息 */
    public static final String SESSION_KEY_USERID = "loginUserId";

    /** 记录删除状态 */
    public static final Integer STATE_DELETED = -1;

    /** 记录有效状态 */
    public static final Integer STATE_VALID = 0;

    /** 工单编号时间戳格式 */
    public static final String ORDER_NUMBER_DATE_YYMMDD = "yyMMdd";
    /** 工单编码序列号 */
    public static final String ORDER_NUMBER_SEQ_SQL = "select sequence_nextval('ORDER_NUMBER_SEQ') as seqNo" ;
    /** 工单编码前缀 */
    public static final String ORDER_NUMBER_PREFIX ="ZL" ;
    /**  工单编码尾序列6位补全*/
    public static final String ORDER_NUMBER_SEQ_FORMAT = "000000";

    /** excel文件存放目录日期格式 */
    public static final String ORDER_EXCEL_DIR_YYYYMMDD = "yyyyMMdd";

    /** 品名列号*/
    public static final int PROD_NAME_CELL_NUM = 0;
    /** 克重列号*/
    public static final int GWEIGHT_CELL_NUM = 1;
    /** 规格列号*/
    public static final int SPEC_CELL_NUM=2;
    /** 数量列号*/
    public static final int AMOUNT_CELL_NUM=3;
    /** 单位列号*/
    public static final int UNIT_CELL_NUM=4;
    /** 单价列号*/
    public static final int UNIT_PRICE_CELL_NUM = 5;
    /** 价格列号*/
    public static final int PRICE_CELL_NUM = 6;
    /** 备注列号*/
    public static final int MEMO_CELL_NUM = 7;


    /** 品名列号*/
    public static final int PROD_NAME_CELL_NUM_2003 = 1;
    /** 克重列号*/
    public static final int GWEIGHT_CELL_NUM_2003 = 3;
    /** 规格列号*/
    public static final int SPEC_CELL_NUM_2003=5;
    /** 数量列号*/
    public static final int AMOUNT_CELL_NUM_2003=7;
    /** 单位列号*/
    public static final int UNIT_CELL_NUM_2003=8;
    /** 单价列号*/
    public static final int UNIT_PRICE_CELL_NUM_2003 = 9;
    /** 价格列号*/
    public static final int PRICE_CELL_NUM_2003 = 10;
    /** 备注列号*/
    public static final int MEMO_CELL_NUM_2003 = 11;

    /** 规格类型常量*/
    public static final String SPEC_TYPE_AREA="长*宽";
    /** 规格类型常量*/
    public static final String SPEC_TYPE_WIDE="宽幅";


}

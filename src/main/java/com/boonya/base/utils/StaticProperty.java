package com.boonya.base.utils;

import java.util.HashMap;
import java.util.Map;

public class StaticProperty {
	public Map<String,Object> map=new HashMap<String,Object>();
	//用户类型 系统管理员
	public final static String  EsUserType_XTGLY="xtgly";
	//用户类型 公司超级管理员
	public final static String  EsUserType_GSCJGLY="gscjgly";
	//用户类型 区域仓库管理员
	public final static String  EsUserType_CKGLY="ckgly";
	//用户类型 单位业务员
	public final static String  EsUserType_CKYWY="ckywy";
	
	//用户类型 业务员
	public final static String  key="weocaocd";
	//与电商的MD5验签KEY
	public final static String 	md5key ="jzt&600998&md5_v01";
	//与电商的加密KEY
	public final static	String deskey="jzt&600998&des*_v01";
	public  static	String errorCode="104";
	//电商平台提供的接口访问地址
	public final static String EC_LOGIN_URL="/bossUser/login";  
	public final static String EC_WAREHOUSER_ADD_URL ="/warehouse/add";
	public final static String EC_WAREHOUSER_UPDATE_URL ="/warehouse/update";
	/**
	 * 波次状态
	 */
	public final static Integer  BATCH_PICKING_NEW= 1; //新增
	
	public final static Integer  BATCH_PICKING_EXE= 2; //已执行
	
	public final static Integer  BATCH_PICKING_ON= 3; //拣货中(当前版本没用)
	
	public final static Integer  BATCH_PICKING_OFF= 4; //已拣货
	
	public final static Integer  BATCH_PICKING_OVER= 5; //已完成
	
	public final static Integer BATCH_BULKPICK_END=6;//已分拣
	
	/**
	 * 拣货策略
	 */
	public final static Integer  BATCH_PICKING_STRATEGY_ZERO= 0;//手动分配
	
	public final static Integer  BATCH_PICKING_STRATEGY_ONE= 1; //先进先出
	
	public final static Integer  BATCH_PICKING_STRATEGY_TWO= 2; //后进先出
	
	public final static Integer  BATCH_PICKING_STRATEGY_THREE= 3; //剩余保质期
	
	/**
	 * 波次执行完后的提示
	 */
	public final static Integer  BATCH_EXE_NO_STOCK_TIP= 1; //找不到库存
	
	public final static Integer  BATCH_EXE_OVERFLOW_TIP= 2; //可用数量小于出库数量
	/**
	 * 波次执行策略
	 */
	public final static Integer  BATCH_EXE_TYPE= 1; //按单
	/**
	 * 货位类型
	 */
	public final static Integer  WARE_LOCATION_STORE= 1; //存储
	public final static Integer  WARE_LOCATION_TEMP= 2; //暂存
	/**
	 * 出库单状态
	 */
	public final static Integer  OUT_ORDER_NEW= 1; //新增
	public final static Integer  OUT_ORDER_SUBMIT= 2; //提交
	public final static Integer  OUT_ORDER_AUDITED_INBATCH= 3; //已加入波次
	public final static Integer  OUT_ORDER_FOUR= 4; //已拣货
	public final static Integer  OUT_ORDER_FIVE= 5; //复核中
	public final static Integer  OUT_ORDER_SIX= 6; //已复核
	public final static Integer  OUT_ORDER_SEVEN= 7; //已装箱
	public final static Integer  OUT_ORDER_EIGHT= 8; //已发货
	public final static Integer  OUT_ORDER_CANCEL= 9; //已取消
	public final static Integer  OUT_ORDER_TEN= 10; //已关闭
	public final static Integer  OUT_ORDER_OUTING=11;//出库中
	public final static Integer  OUT_ORDER_OUTED=12;//已出库确认
	public final static Integer  OUT_ORDER_REJECT=13;//已拒绝
	public final static Integer  OUT_ORDER_FOURTEEN= 14; //装箱中
	public final static Integer  OUT_ORDER_FIFTEEN=15;//运单中
	public final static Integer  OUT_ORDER_SIXTEEN=16;//运单完成
	public final static Integer  OUT_ORDER_SEVENTEEN=17;//已分配电子面单
	
	/**
	 * 出库单来源
	 */
	public final static Integer  OUT_ORDER_SOURCE_EM= 1; //电商创建
	public final static Integer  OUT_ORDER_SOURCE_YEWU= 2; //业务员手动创建
	public final static Integer  OUT_ORDER_SOURCE_DB= 3; //调拨出库
	/**
	 * 仓单状态
	 */
	public final static Integer  FINANCE_ORDER_NEW= 1; //新增
	public final static Integer  FINANCE_ORDER_VALID= 2; //有效
	public final static Integer  FINANCE_ORDER_FROZEN= 3; //冻结
	public final static Integer  FINANCE_ORDER_INVALID= 4; //完成
	
	/**
	 * 收货明细状态
	 */
	public final static Integer  INTO_ORDER_RECEIPT= 0; //未上架			
	public final static Integer  INTO_ORDER_RECEIPTONE= 1; //已上架
	
	/**
	 * 入库单商品明细状态
	 */
	public final static Integer  INTO_ORDER_INFO_ONE= 1; //已收货
	public final static Integer  INTO_ORDER_INFO_TWO= 2; //上架
	public final static Integer  INTO_ORDER_INFO_EIGHT= 8; //新建
	
	/**
	 * 入库单状态
	 */
	public final static Integer INTO_ORDER_ZERO = 0;//未提交
	public final static Integer INTO_ORDER_ONE = 1;//已提交
	public final static Integer  INTO_ORDER_TWO = 2; //已收货
	public final static Integer  INTO_ORDER_THREE = 3; //已质检
	public final static Integer  INTO_ORDER_FOUR = 4; //已上架
	public final static Integer  INTO_ORDER_FIVE = 5; //已关闭
	
	/**
	 * 提交状态(已提交(1)，未提交(0){提交以后仓库人员才能看见})	
	 */
	public final static Integer  WMSP_SUNSTATUS_ZERO= 0; //新建
	public final static Integer  WMSP_SUNSTATUS_ONE=1; //已提交
	public final static Integer  WMSP_SUNSTATUS_TWO=2; //已拒收
	
	public final static Integer  WMOQ_QC_STATUS_ZERO= 0; //停用
	public final static Integer  WMOQ_QC_STATUS_ONE=1; //启用
	
	/**
	 * 抽样检验状态（默认抽检中（1），通过（2），不通过（4），已抽检（3）））
	 */
	public final static Integer  WMSP_INSPECTION_ONE= 1;
	public final static Integer  WMSP_INSPECTION_TWO= 2;
	public final static Integer  WMSP_INSPECTION_FOUR= 4;
	public final static Integer WMSP_INSPECTION_THREE= 3;
	
	/**
	 * 盘点的状态(1新增，2盘点中，3盘点完成,4已复盘)
	 */
	public final static Integer INVENTORY_STATUS_ONE = 1;
	public final static Integer INVENTORY_STATUS_TWO = 2;
	public final static Integer INVENTORY_STATUS_THREE = 3;
	public final static Integer INVENTORY_STATUS_FOUR = 4;
	
	/**
	 * 异常状态(0正常，1待处理，2处理完成)
	 */
	public final static Integer INVENTORY_DETAIL_EXCEPTION_STATE_ZERO = 0;
	public final static Integer INVENTORY_DETAIL_EXCEPTION_STATE_ONE = 1;
	public final static Integer INVENTORY_DETAIL_EXCEPTION_STATE_TWO = 2;
	
	/**
	 * 拣货策略
	 */
	public final static int  ITEM_VALUE_TYPE_INTO= 1;//入库计算数量	
	public final static int  ITEM_VALUE_TYPE_OUT= 2; //出库计算数量	
	public final static int  ITEM_VALUE_TYPE_START_STOCK= 3; //期初库存数量
	public final static int  ITEM_VALUE_TYPE_END_STOCK= 4; //期末库存数量
	public final static int  ITEM_VALUE_TYPE_UNPICK= 5; //整散拆整数量
	public final static int  ITEM_VALUE_TYPE_ADJUST= 6; //库存调整数量
	public final static int  ITEM_VALUE_TYPE_PROFIT= 7; //盘点收益数量
	
	public final static int  PICK_STRATEGY_ZRO= 0;//手动分配
	public final static int  PICK_STRATEGY_ONE= 1; //先进先出	
	public final static int  PICK_STRATEGY_THREE=2; //后进先出
	public final static int  PICK_STRATEGY_FOUR= 3; //剩余保质期
	
	/**
	 * saas接口默认用户名密码
	 */
	public final static String INTERFACE_SAAS_DEFAULT_USER_NAME = "wmsadmin";
	public final static String INTERFACE_SAAS_DEFAULT_USER_PWD = "123456";
	
	/**
	 * wms超级用户登录(开发入口)
	 */
	public final static String SUPPER_DEFAULT_USER_NAME = "superwlydadmin";
	public final static String SUPPER_DEFAULT_USER_DOMAIN = "10000";
	public final static String SUPPER_DEFAULT_USER_PWD = "Super_Admin_WLYD";
	public final static String SUPPER_DEFAULT_ROLE_RIGHTS = "3188165077490141233843064877755125511831336933862227031755849768";
	
	/**
	 * OMS与WMS接口类型定义
	 */
	public final static int OMS_WMS_INTERFACE_GETCUSTOMER = 10001;
	public final static int OMS_WMS_INTERFACE_UPDATEPRODUCT = 10002;
	public final static int OMS_WMS_INTERFACE_GETORDER = 10003;
	public final static int OMS_WMS_INTERFACE_GETRETURNORDER = 10004;
	public final static int OMS_WMS_INTERFACE_UPDATEPRODUCTSTOCK = 10005;
    public final static int OMS_WMS_INTERFACE_UPDATEORDERSTATUS = 10006;
    public final static int OMS_WMS_INTERFACE_GETCANCELORDER = 10007;
    
    /**
	 * WAAS与WMS接口类型定义
	 */
	public final static int WAAS_WMS_INTERFACE_GETCUSTOMERS = 20001;//获取客户(货主)接口-
	public final static int WAAS_WMS_INTERFACE_GETCARRIERS = 20002;// 获取承运商信息接口-
	public final static int WAAS_WMS_INTERFACE_GETWARHOUSES = 20003;// 获取仓库信息接口-
	public final static int WAAS_WMS_INTERFACE_GETPRODUCTS = 20004;// 获取商品信息接口-
	public final static int WAAS_WMS_INTERFACE_GETORDERS = 20005;// 订单任务下发接口-
	public final static int WAAS_WMS_INTERFACE_GETCANCELORDER = 20006;//订单中止或取消-
	public final static int WAAS_WMS_INTERFACE_RETURNORDER = 20007;//订单退货接口-
	public final static int WAAS_WMS_INTERFACE_UPDATEORDERSTATUS = 20008;//订单处理结果接口
	public final static int WAAS_WMS_INTERFACE_UPDATEPRODUCTSTOCK = 20009;//库存同步接口
    public final static int WAAS_WMS_INTERFACE_SEND_ELECTRIC_ORDER = 20010;//电子面单接口
    public final static String WAAS_WMS_INTERFACE_SERIALNO = "SERIALNO";//业务员序列号标识
    
    /**
     * 接口请求最大参数
     */
    public final static int INTERFACE_REQUEST_MAX_PAGESIZE = 100;

    /**
     * OMS与WMS同步接口日志
     */
    public final static int OMS_WMS_INTERFACE_LOG_SYS_CUSTOMER = 3;//客户
    public final static int OMS_WMS_INTERFACE_LOG_SYS_ITEM = 4;//商品
    
    public final static String WAAS_API_ISENABLE="WAAS_API_ISENABLE";//WAAS接口控制变量
    public final static String WAAS_MEMBERCODE="WAAS_MEMBERCODE";//WAAS接口服务商编码
    
    /**
     * WMS对外开放接口配置变量
     */
    public final static String WMS_OPEN_API_PLATFORM="WMS_OPEN_API_PLATFORM";//WMS开放接口平台
    public final static String WMS_OPEN_API_MEMBERCODE="WMS_OPEN_API_MEMBERCODE";//WMS开放接口服务商编码
    public final static String WMS_OPEN_API_ISENABLE="WMS_OPEN_API_ISENABLE";//WMS开放接口是否启用
    public final static String WMS_OPEN_API_RSA_PUBLIC_KEY="WMS_OPEN_API_RSA_PUBLIC_KEY";//WMS开放接口RSA公钥
    public final static String WMS_OPEN_API_RSA_PRIVATE_KEY="WMS_OPEN_API_RSA_PRIVATE_KEY";//WMS开放接口RSA私钥
    
    /**
	 * 接口请求缓存对象名称
	 */
	public static final String REQUESTCACHE="REQUESTCACHE";
}

package FrameWork;

public class CommonConstant {
    public static String orderid="";

    public static boolean Local_run = true;
    public static String Platfrom_name = "Android";
    //public static String Platfrom_name = "IOS";
    public static String Select_Country = "Qatar";
    public static  String Product_Name = "test";
    public static String DeviceName = "00008020-00021DCC227A002E";
    public static String LoginUsrname = "qaautomation@test.com";
    public static String Loginpsword = "Qa@test";
    public static String Magento_DBURL = "jdbc:mysql://172.31.9.188:3306/boutiqaat_v2";
    public static String MLDB_url = "jdbc:mysql://172.31.9.188:3306/beta_ml";
    public static String username = "qa";
    public static String Password = "31qa21";
    public static String  Magento_query_order = "Select a.store_id, a.grand_total, a.shipping_amount, a.subtotal, a.total_qty_ordered, a.total_due, a.customer_email, a.global_currency_code, a.order_currency_code, a.store_currency_code, a.msp_cod_amount, a.payment_method, b.store_id, b.product_id, b.product_type, b.sku, b.name, b.qty_ordered, b.price, b.base_price, b.original_price, b.row_total, b.price_incl_tax, b.row_total_incl_tax, b.commision_percent, b.commision_amount, b.celebrity_special_price from sales_order a join sales_order_item b on (a.entity_id = b.order_id) where a.reference_number = '$orderid'";
    public static String ML_query_order = "select a.payment, a.store_id, a.device_type, a.tx_amount, a.currency, a.shipping_charge, a.converted_order_total, b.quantity, b.unit_price, b.total_price, b.name, b.sku, b.celebrity_id from order_queue a join order_item_queue b on (a.id = b.order_temp_id) where a.increment_id = '$orderid'";
    public static String  Magento_GiftCard = "select increment_id from sales_order where reference_number = '$orderid'";
    public static String  GiftCardBalance_query = "select amount from magento_customerbalance where customer_id = 2081257;";
    public static String sku = "1122334455";
    public static String PreProdsku = "ORL-00001836";
    public static String GiftcardProd = "GF-000000122";
    public static String PreprodGiftcardProd = "GF-000000122";
    public static String prodname = "QAAutomationSimple";
    public static String Preprodprodname = "QAAutomationSimple";
    //public static String prodname = "BhawTest";
    public static String ConfigProdName = "QAConfigProdL";
    public static String PreprodConfigProdName = "QAConfigProdL";
    public static String TVProdName = "BhawTest";
    public static String PreprodTV = "Black Opal by Gissah";
    public static String ExclusiveProdName = "AutomationExclusiveProd";
    public static String PreprodExclusiveProdName = "AutomationExclusiveProd";
    public static String Discountprodname = "DiscountAutomationProduct";
    public static String PreprodDiscountprodname = "DiscountAutomationProduct";
    public static String Makeupprodname = "BhawTestItem";
    public static String PreprodMakeupprodname = "BhawTestItem";
    public static String CelebName ="QATestCeleb-2020";
    public static String PreprodCelebName ="QATestCeleb-2020";
    public static String CelebProdName = "QACelebProd";
    public static String PreprodCelebProdName = "QACelebProd";
    public static String CelebSecProdName = "testcelebSecondProd";
    public static String CeleCatbName ="Aaqshah Boutique";
    public static String SecondCelebName ="testceleb-automationn";
    public static String PreprodSecondCelebName ="testceleb-automationn";
    public static String sku_conf = "20190705";
    public static String Preprodsku_conf = "20190705";
    public static String sku_Exc = "20190709";
    public static String Preprodsku_Exc = "20190709";
    public static String sku_SecCeleb = "44556677";

    public static String sku_Discount = "20190626";
    public static String Preprodsku_Discount = "20190626";
    public static String Knet_Bank = "Knet Test Card [KNET1]";
    public static String Knet_Cardnumber = "0000000001";
    public static String Knet_MM = "09";
    public static String Knet_YY = "2021";
    public static String Knet_Pin = "1234";
    public static String ML_Celeb_id = "select b.celebrity_id from order_queue a join order_item_queue b on (a.id = b.order_temp_id) where a.increment_id = 'KW2588159005696110'";


}

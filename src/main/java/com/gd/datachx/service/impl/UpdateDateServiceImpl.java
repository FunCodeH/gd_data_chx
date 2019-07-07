package com.gd.datachx.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gd.datachx.entity.*;
import com.gd.datachx.mapper.*;
import com.gd.datachx.service.UpdateDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @Auther: He Wenbo
 * @Date: 2019/6/25
 * @Description:
 */
@Service
public class UpdateDateServiceImpl implements UpdateDateService {

    @Autowired
    private TBdBranchInfoMapper tBdBranchInfoMapper;

    @Autowired
    private TBdItemBrandMapper tBdItemBrandMapper;

    @Autowired
    private TBdItemClsMapper tBdItemClsMapper;

    @Autowired
    private TRmVipInfoMapper tRmVipInfoMapper;

    @Autowired
    private TBdSupcustInfoMapper tBdSupcustInfoMapper;

    @Autowired
    private TBdPaymentInfoMapper tBdPaymentInfoMapper;

    @Autowired
    private TSysOperatorMapper tSysOperatorMapper;

    @Autowired
    private TRmSalemanMapper tRmSalemanMapper;

    @Autowired
    private TBdItemInfoMapper tBdItemInfoMapper;

    @Autowired
    private TImBranchStockMapper tImBranchStockMapper;

    @Autowired
    private TRmVipSavelistMapper tRmVipSavelistMapper;

    @Autowired
    private TRmVipAcclistMapper tRmVipAcclistMapper;

    @Autowired
    private TRmPayflowMapper tRmPayflowMapper;

    @Autowired
    private TRmSaleflowMapper tRmSaleflowMapper;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Date rechargeRecordDate = null;
    private static Date saleDetailDate = null;
    private static Date carPayDate = null;
    private static Date itemInfoDate = null;
    private static Date orderDate = null;

    @Override
    public String queryCashierList() {
        List<TSysOperator> tSysUsers = tSysOperatorMapper.selectCashierList();
        JSONArray cashiers = new JSONArray();
        for (TSysOperator user : tSysUsers) {
            JSONArray cashier = new JSONArray();
            cashier.add(user.getOperId());
            cashier.add(user.getOperName());
            cashier.add(user.getBranchNo());
            cashiers.add(cashier);
        }
        return JSONObject.toJSONString(cashiers);
    }

    @Override
    public String queryBrandList() {
        List<TBdItemBrand> tItemBrands = tBdItemBrandMapper.selectBrandList();
        JSONArray brands = new JSONArray();
        for (TBdItemBrand tItemBrand : tItemBrands) {
            JSONArray brand = new JSONArray();
            brand.add(tItemBrand.getItemBrandno());
            brand.add(tItemBrand.getItemBrandname());
            brands.add(brand);
        }
        return JSONObject.toJSONString(brands);
    }

    @Override
    public String queryModeItemCls() {
        List<TBdItemCls> itemClasses = tBdItemClsMapper.selectItemCls();
        JSONArray tItemClasses = new JSONArray();
        for (TBdItemCls tItemClass : itemClasses) {
            JSONArray itemCls = new JSONArray();
            itemCls.add(tItemClass.getItemClsno());
            itemCls.add(tItemClass.getItemClsname());
            itemCls.add(tItemClass.getClsParent());
            tItemClasses.add(itemCls);
        }
        return JSONObject.toJSONString(tItemClasses);
    }

    @Override
    public String queryModeSaleMan() {
        List<TRmSaleman> tSysUsers= tRmSalemanMapper.selectSaleManList();
        JSONArray saleMans = new JSONArray();
        for (TRmSaleman user : tSysUsers) {
            JSONArray saleMan = new JSONArray();
            saleMan.add(user.getSaleId());
            saleMan.add(user.getSaleName());
            saleMans.add(saleMan);
        }
        JSONArray saleMan = new JSONArray();
        saleMan.add("9999");
        saleMan.add("默认营业员");
        saleMans.add(saleMan);
        return JSONObject.toJSONString(saleMans);
    }

    @Override
    public String querySupcustInfo() {
        List<TBdSupcustInfo> tSupInfos= tBdSupcustInfoMapper.selectSupcust();
        JSONArray supcusts = new JSONArray();
        for (TBdSupcustInfo tSupInfo : tSupInfos) {
            JSONArray supcust = new JSONArray();
            supcust.add(tSupInfo.getSupcustNo());
            supcust.add(tSupInfo.getSupName());
            supcust.add(tSupInfo.getSupMan());
            supcust.add(tSupInfo.getSupAddr());
            supcusts.add(supcust);
        }
        return JSONObject.toJSONString(supcusts);
    }

    @Override
    public String queryDataPayment() {
        List<TBdPaymentInfo> tDataPaymentList= tBdPaymentInfoMapper.selectPayments();
        JSONArray tdataPayments = new JSONArray();
        for (TBdPaymentInfo payment : tDataPaymentList) {
            JSONArray tdataPayment = new JSONArray();
            tdataPayment.add(payment.getPayWay());
            tdataPayment.add(payment.getPayName());
            tdataPayments.add(tdataPayment);
        }
        return JSONObject.toJSONString(tdataPayments);
    }

    @Override
    public String queryVisaFlow() {
        List<TRmVipSavelist> visaFlows= tRmVipSavelistMapper.selectVisaFlows(rechargeRecordDate);
        JSONArray tVisaFlows = new JSONArray();
        for (TRmVipSavelist tVisaFlow : visaFlows) {
            JSONArray visaFlow = new JSONArray();
            visaFlow.add(tVisaFlow.getFlowId().toString());
            visaFlow.add(tVisaFlow.getConsumAmt().doubleValue());
            visaFlow.add(simpleDateFormat.format(tVisaFlow.getOpeDate()));
            visaFlow.add(tVisaFlow.getBranchNo());
            rechargeRecordDate = tVisaFlow.getOpeDate();
            tVisaFlows.add(visaFlow);
        }
        return JSONObject.toJSONString(tVisaFlows);
    }

    @Override
    public String querySaleDetail() {
        List<TRmSaleFlowInfo> tPosSales = tRmSaleflowMapper.selectSaleDetail(saleDetailDate);
        JSONArray posSales = new JSONArray();
        for (TRmSaleFlowInfo tPosSale : tPosSales) {
            JSONArray posSale = new JSONArray();
            posSale.add(tPosSale.getComNo());
            posSale.add("0");
            posSale.add(simpleDateFormat.format(tPosSale.getOperDate()));
            if(tPosSale.getSellWay().equals("A")) {
                posSale.add(tPosSale.getSaleMoney().doubleValue());
            }else if(tPosSale.getSellWay().equals("C")){
                posSale.add(BigDecimal.ZERO.doubleValue());
            }
            posSale.add(tPosSale.getSaleQnty().doubleValue());
            posSale.add(tPosSale.getFlowNo());
            posSale.add(tPosSale.getItemNo());
            posSale.add(tPosSale.getSaleMan());
            posSale.add(tPosSale.getPosid());
            if(tPosSale.getSellWay().equals("A")) {
                posSale.add(BigDecimal.ZERO.doubleValue());
            }else if(tPosSale.getSellWay().equals("C")){
                posSale.add(tPosSale.getSourcePrice().doubleValue());
            }
            posSale.add(tPosSale.getOperId());
            posSale.add(tPosSale.getSellWay());
            posSale.add(tPosSale.getBranchNo());
            saleDetailDate = tPosSale.getOperDate();
            posSales.add(posSale);
        }
        return JSONObject.toJSONString(posSales);
    }

    @Override
    public String queryCardPaylist() {
        List<TRmVipAcclist> tVipFlowList = tRmVipAcclistMapper.selectCarPayList(carPayDate);
        JSONArray tVipFlows = new JSONArray();
        for (TRmVipAcclist tVipFlow : tVipFlowList) {
            JSONArray vipFlow = new JSONArray();
            vipFlow.add(tVipFlow.getFlowId().toString());
            vipFlow.add(tVipFlow.getCardNo());
            vipFlow.add(tVipFlow.getConsumAmt().doubleValue());
            vipFlow.add(simpleDateFormat.format(tVipFlow.getOpeDate()));
            vipFlow.add(tVipFlow.getBranchNo());
            carPayDate = tVipFlow.getOpeDate();
            tVipFlows.add(vipFlow);
        }
        return JSONObject.toJSONString(tVipFlows);
    }

    @Override
    public String queryVipInfo() {
        List<TRmVipInfo> tVipInfoList = tRmVipInfoMapper.selectVipInfo();
        JSONArray tvipInfos = new JSONArray();
        for (TRmVipInfo tVipInfo : tVipInfoList) {
            JSONArray vipInfo = new JSONArray();
            vipInfo.add(tVipInfo.getCardNo());
            vipInfo.add(tVipInfo.getVipName());
            vipInfo.add(tVipInfo.getMobile());
            vipInfo.add(simpleDateFormat.format(tVipInfo.getOperDateGi()));
            vipInfo.add(tVipInfo.getBranchNo());
            tvipInfos.add(vipInfo);
        }
        return JSONObject.toJSONString(tvipInfos);
    }

    @Override
    public String queryItemInfo() {
        List<TitemStockInfo> infoList = tBdItemInfoMapper.selectItemInfo();
        JSONArray infos = new JSONArray();
        for (TitemStockInfo stockInfo : infoList) {
            JSONArray info = new JSONArray();
            info.add(stockInfo.getItemBarcode());
            info.add(stockInfo.getItemInPrice().floatValue());
            info.add(stockInfo.getStockQty() == null ? 0: stockInfo.getStockQty());
            info.add(stockInfo.getItemName());
            info.add(stockInfo.getItemBrand());
            info.add(stockInfo.getItemClass());
            info.add(stockInfo.getItemNo());
            info.add(stockInfo.getItemSize());
            info.add(stockInfo.getItemUnitNo());
            info.add(simpleDateFormat.format(stockInfo.getModifyDate() == null ? stockInfo.getCreateTime() : stockInfo.getModifyDate()));
            info.add(stockInfo.getItemSalePrice().floatValue());
            info.add(stockInfo.getItemVipPrice().floatValue());
            info.add(stockInfo.getItemSupNo());
            info.add("All");
            info.add(simpleDateFormat.format(stockInfo.getCreateTime()));
//            itemInfoDate = stockInfo.getModifyDate();
            infos.add(info);
        }
        return JSONObject.toJSONString(infos);
    }

    @Override
    public String queryItemStock() {
        List<TImBranchStock> titemStockList = tImBranchStockMapper.selectTitemStock();
        JSONArray stocks = new JSONArray();
        for (TImBranchStock titemStock : titemStockList) {
            JSONArray stock = new JSONArray();
            stock.add(titemStock.getItemNo());
            stock.add(titemStock.getStockQty().floatValue());
            stock.add(titemStock.getAvgCost().floatValue());
            stock.add(titemStock.getBranchNo());
            stocks.add(stock);
        }
        return JSONObject.toJSONString(stocks);
    }

    @Override
    public String queryOrderDetail() {
        List<TRmPayflow> tOrderDetailList = tRmPayflowMapper.selectOrderDetail(orderDate);
        JSONArray tOrderDetails = new JSONArray();
        for (TRmPayflow detail : tOrderDetailList) {
            JSONArray tOrderDetail = new JSONArray();
            tOrderDetail.add(detail.getComNo());
            tOrderDetail.add(detail.getFlowId());
            tOrderDetail.add(detail.getFlowNo());
            tOrderDetail.add(detail.getSaleAmount().doubleValue());
            tOrderDetail.add(detail.getPayWay());
            tOrderDetail.add(detail.getSellWay());
            tOrderDetail.add(simpleDateFormat.format(detail.getOperDate()));
            tOrderDetail.add(detail.getPayAmount().doubleValue());
            tOrderDetail.add(detail.getVipNo());
            tOrderDetail.add(detail.getCardNo());
            tOrderDetail.add(detail.getBranchNo());
            orderDate = detail.getOperDate();
            tOrderDetails.add(tOrderDetail);
        }
        return JSONObject.toJSONString(tOrderDetails);
    }

    @Override
    public String queryBranchInfoList() {
        List<TBdBranchInfo> branchfos = tBdBranchInfoMapper.selectBranchInfos();
        JSONArray tBranchfos = new JSONArray();
        for (TBdBranchInfo tBranchfo : branchfos) {
            JSONArray branchinfo = new JSONArray();
            branchinfo.add(tBranchfo.getBranchName());
            branchinfo.add(tBranchfo.getBranchMan());
            branchinfo.add(tBranchfo.getAddress());
            branchinfo.add(tBranchfo.getBranchNo());
            tBranchfos.add(branchinfo);
        }
        return JSONObject.toJSONString(tBranchfos);
    }

    public static void main(String[] args) {
        System.out.println(Long.parseLong(("190626000100001".substring(0,6)) + ("190626000100001".substring(10,15))));
//        190626000100001
    }
}

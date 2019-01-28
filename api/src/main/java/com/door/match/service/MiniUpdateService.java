package com.door.match.service;

import com.door.match.entity.*;
import com.door.match.mapper.RegMapperMapper;
import com.door.match.mapper.RegUserMapper;
import com.door.match.mapper.UpdateMapper;
import com.door.match.paysdk.*;
import com.door.match.utils.DateUtil;
import com.door.match.utils.OSSClientUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

@Service
public class MiniUpdateService {

    @Autowired
    UpdateMapper updateMapper;
    @Autowired
    RegUserMapper regUserMapper;
    @Autowired
    OSSClientUtil ossClientUtil;

    //修改资料前的查询
    public Map select(String openid){
        Map map = new HashMap();
        ArrayList<AgeRank> ageRank = regUserMapper.reqAgeRank();
        ArrayList<SalaryRank> salaryRank = regUserMapper.reqSalarkRank();
        ReqData2 reqData = updateMapper.selectByUser(openid);
        ArrayList<Profession> listparam= regUserMapper.reqProfessionByParentID(1);
        if(listparam!=null&&listparam.size()>0){
            for (Profession profession:listparam ) {
                ArrayList<Profession> listparamchildren= regUserMapper.reqProfessionByParentID(profession.getId());
                if(listparamchildren!=null){
                    profession.setChildren(listparamchildren);
                }
            }
        }
        map.put("ageArray",ageRank);
        map.put("listparam",listparam);
        map.put("salaryArray",salaryRank);
        map.put("reqData",reqData);
        System.out.println(map);
        return map;
    }

    @Transactional
    //修改资料
    public Integer update(ReqData2 reqData){
        MultipartFile file = reqData.getFile();
        String name = ossClientUtil.uploadImg2Oss(file);
        String imgUrl = ossClientUtil.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        String url=split[0];
        Long id = updateMapper.selectUserId(reqData.getOpenid());
        reqData.setAddress(reqData.getAddress().replaceAll("全部","").replaceAll("@@@@",""));
        reqData.setAddress2(reqData.getAddress2().replaceAll("全部","").replaceAll("@@@@",""));
        reqData.setId(id);
        reqData.setLogo(url);
        Integer i = updateMapper.updateUser(reqData);
        Integer j = updateMapper.updateUserMapping(reqData);
        if (i==1&&j==1){
            return 1;
        }else{
            throw new RuntimeException();
        }
    }

    //个人主页的查询
    public Map userSelect(ReqData mid){
        HashMap map = new HashMap();
        MapperRecordInfo age = updateMapper.getmapperinfo(mid);
        Integer partnermacdeg=updateMapper.partnermacdeg(age);
        age.setPartnermacdeg(partnermacdeg);
        map.put("reqData",age);
        return map;
    }

    //添加图片
    public Integer addImg(ReqData2 reqData2){
        MultipartFile file = reqData2.getFile();
        String name = ossClientUtil.uploadImg2Oss(file);
        String imgUrl = ossClientUtil.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        String url=split[0];
        String openid = reqData2.getOpenid();
        Long reg_id = updateMapper.selectUserId(openid);
        UserImg userImg = new UserImg();
        userImg.setRegid(reg_id);
        userImg.setUserimg(url);
        Integer num = updateMapper.addImg(userImg);
        return num;
    }

    public Integer deletimg(ReqData imgid) {

       int count= updateMapper.deletimg(imgid);
        if(count>0){

            return count;
        }
        return null;
    }

    public ArrayList<UserImg> getuserimg(ReqData imgid) {
        if(imgid.getMid()!=null&&imgid.getMid()>0){
            ArrayList<UserImg> imgList = updateMapper.SelectUserImg(imgid.getMid());
            if(imgList!=null&&imgList.size()>0){
                return imgList;
            }
        }else{
            ArrayList<UserImg> imgList = updateMapper.SelectUserImgByOid(imgid);
            if(imgList!=null&&imgList.size()>0){
                return imgList;
            }
        }
        return null;
    }

    public MapperRecordInfo getuserinfo(ReqData oid) {
        MapperRecordInfo age = updateMapper.getuserinfo(oid);
        return age;
    }
    @Transactional
    public Integer updatenoimg(ReqData2 reqData) {
        Long id = updateMapper.selectUserId(reqData.getOpenid());
        reqData.setId(id);
        reqData.setAddress(reqData.getAddress().replaceAll("全部","").replaceAll("@@@@",""));
        reqData.setAddress2(reqData.getAddress2().replaceAll("全部","").replaceAll("@@@@",""));

        Integer i = updateMapper.updateUser(reqData);
        Integer j = updateMapper.updateUserMapping(reqData);
        if (i==1&&j==1){
            return 1;
        }else{
            throw new RuntimeException();
        }
    }

    public Map rechargeapply(ReqData imgid) throws Exception {

        Map<String, String> map = Maps.newLinkedHashMap();
        WXPay wxpay = new WXPay(MyWXPayConfig.apiMCH());
        int total_fee = imgid.getMoney().multiply(new BigDecimal(100)).intValue();
        map.put("time_start", DateUtil.date2String(new Date(), "yyyyMMddHHmmss"));
        map.put("notify_url", Common.getConfig("callbackip") + "/callbackUnifiedorder");
        map.put("trade_type", "JSAPI");
        joinUnifiedorder("0", "蓦然天合解锁支付", total_fee + "", imgid.getOpenid(), map);
        wxpay.fillRequestData(map);
        Map<String, String> strXML = wxpay.unifiedOrder(map);
        System.out.println(map.toString());
        if(WXPayConstants.SUCCESS.equals(strXML.get("return_code"))
                && WXPayConstants.SUCCESS.equals(strXML.get("result_code"))) {
            strXML = joinPay(map, strXML, wxpay);
            map.put("prepay_id", strXML.get("prepay_id"));
            map.put("flag", "4");
            map.put("userid", updateMapper.selectUserId(imgid.getOpenid()).toString());
            map.put("way", "2");
            int count = insertPay(map);
            if(count > 0) {
                strXML.put("outTradeNo", map.get("out_trade_no"));
                return strXML;
            }
        }
        return  null;
    }
    private void joinUnifiedorder(String deviceinfo, String body, String total_fee, String openid, Map<String, String> map) {
        map.put("device_info", deviceinfo); //设备号
        map.put("body", body);  //商品描述
        map.put("out_trade_no", WXPayUtil.outTradeNo());
        map.put("total_fee", total_fee);
        map.put("spbill_create_ip", Common.getIpAddr());
        map.put("fee_type", "CNY");
        map.put("openid", openid);
    }
    /**
     * @Author dubin
     * @Description //插入支付订单
     * @Date 10:34 2019/1/28
     * @Param [map]
     * @return int
     **/


    private int insertPay(Map<String, String> map) {
        PayPO pay = new PayPO();
        BigDecimal total = new BigDecimal(map.get("total_fee")).divide(new BigDecimal(100));

        pay.setUserId(Integer.parseInt(map.get("userid")));
        pay.setPayNo(map.get("out_trade_no"));
        //pay.setTranscationId(map.get("prepay_id"));
        pay.setFlag(Integer.parseInt(map.get("flag")));
        pay.setWay(Integer.valueOf(map.get("way")));
        pay.setMoney(total);
        pay.setTrueMoney(total);
        pay.setStatus(0);
        pay.setCreatedAt(new Date());

        return updateMapper.insertSelective(pay);
    }
    private Map<String, String> joinPay(Map<String, String> map, Map<String, String> strXML, WXPay wxpay) throws Exception {
        Map<String, String> param = Maps.newLinkedHashMap();
        param.put("appId", map.get("appid"));
        param.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");
        param.put("nonceStr", map.get("nonce_str"));
        param.put("package", "prepay_id=" + strXML.get("prepay_id"));
        param.put("signType", WXPayConstants.SignType.HMACSHA256.name());
        param.put("paySign", wxpay.signatureData(param, WXPayConstants.SignType.HMACSHA256));
        param.remove("appId");
        return param;
    }
/**
 * @Author dubin
 * @Description //支付回调
 * @Date 10:34 2019/1/28
 * @Param [request]
 * @return java.lang.String
 **/


    public String callbackUnifiedorderService(HttpServletRequest request) {
        try {
            InputStream inStream = request.getInputStream();
            int _buffer_size = 1024;
            if (inStream != null) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] tempBytes = new byte[_buffer_size];
                int count = -1;
                while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
                    outStream.write(tempBytes, 0, count);
                }
                outStream.flush();
                String result = new String(outStream.toByteArray(), "UTF-8");
                WXPay wxpay = new WXPay(MyWXPayConfig.apiMCH());
                Map<String, String> strXML = wxpay.processResponseXml(result);
                if(WXPayConstants.SUCCESS.equals(strXML.get("return_code"))
                        && WXPayConstants.SUCCESS.equals(strXML.get("result_code"))) {
                    return verifyUnifiedorder(strXML);
                }
            }
        } catch (Exception e) {

        }
        return "fail";
    }
    /**
     * @Author dubin
     * @Description //支付回调验证
     * @Date 10:34 2019/1/28
     * @Param [strXML]
     * @return java.lang.String
     **/


    private String verifyUnifiedorder(Map<String, String> strXML) {
        Map<String, Object> paramMap = Maps.newHashMap();
        String out_trade_no = strXML.get("out_trade_no");
        int total_fee = Integer.valueOf(strXML.get("total_fee"));
        paramMap.put("pay_no", out_trade_no);
        PayPO pay = updateMapper.selectSelectiveOne(paramMap);

        int status = pay.getStatus().intValue();
        if(0 == status) {
            if(total_fee == pay.getMoney().multiply(new BigDecimal(100)).intValue()) {
                int flag = pay.getFlag();
                int res = updatePay(strXML, out_trade_no);

            }
        }else {
            return "fail";
        }
        return "fail";
    }
    /**
     * @Author dubin
     * @Description //更新支付订单
     * @Date 10:28 2019/1/28
     * @Param [strXML, out_trade_no]
     * @return int
     **/


    private int updatePay(Map<String, String> strXML, String out_trade_no) {
        Map<String, Object> paramMap = Maps.newHashMap();

        paramMap.put("pay_no", out_trade_no);
        paramMap.put("transcationId", strXML.get("transaction_id"));
        paramMap.put("notifyAt", DateUtil.string2Timestamp(strXML.get("time_end"), "yyyyMMddHHmmss"));
        paramMap.put("updatedAt", DateUtil.currentTimestamp());
        paramMap.put("true_money", new BigDecimal(strXML.get("cash_fee")).divide(new BigDecimal(100), 2));
        paramMap.put("status", 1);

        return updateMapper.updateByPrimaryKeySelective(paramMap);
    }
    @Transactional
    public Integer orderquery(String outTradeNo) throws Exception{
        Map<String, Object> paramMap = Maps.newHashMap();
        Map<String, String> param = Maps.newLinkedHashMap();
            paramMap.put("pay_no", outTradeNo);
            PayPO pay = updateMapper.selectSelectiveOne(paramMap);
            Preconditions.checkNotNull(pay, "根据订单号查询订单为空[pay_no:%s]", outTradeNo);
            if(pay.getStatus().intValue() == 1)
                return 1;
            WXPay wxpay = new WXPay(MyWXPayConfig.apiMCH());
            param.put("out_trade_no", outTradeNo);
            wxpay.fillRequestData(param);
            Map<String, String> strXML = wxpay.orderQuery(param);
            if(WXPayConstants.SUCCESS.equals(strXML.get("return_code"))
                    && WXPayConstants.SUCCESS.equals(strXML.get("result_code"))) {
                if("SUCCESS".equals(strXML.get("trade_state"))) {
//                    UserPO user = userMapper.selectByPrimaryKey(pay.getUserId());
//                    int flag = pay.getFlag();
                    int res = updatePay(strXML, pay.getPayNo());
                    return res;
//                    if(2 == flag) { //支付订单
//                        res = updateOrderStates(user.getCurrentOrder());
//                        res = updateUser(pay.getUserId(), pay.getMoney());
//                        if(res > 0) return result;
//                    }else if(4 == flag) { //充值订单
//                        res = updateWalletUser(pay.getUserId(), pay.getMoney());
//                        if(res > 0) return result;
//                    }else {
//                        logger.warn("交易类型错误, [out_trade_no:{}, flag:{}]", pay.getPayNo(), flag);
//                    }
                }
            }

        return 0;


    }
}

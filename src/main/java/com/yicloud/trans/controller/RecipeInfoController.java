package com.yicloud.trans.controller;

import cn.hutool.Hutool;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yicloud.trans.model.mssql.Jbxxk;
import com.yicloud.trans.model.mssql.MzypmxkF;
import com.yicloud.trans.model.mssql.Mzyskfk;
import com.yicloud.trans.model.mssql.XymxkF;
import com.yicloud.trans.model.mssql.zd.YpCdWrapper;
import com.yicloud.trans.model.mysql.*;
import com.yicloud.trans.service.mssql.MzypmxkFService;
import com.yicloud.trans.service.mssql.MzyskfkService;
import com.yicloud.trans.service.mssql.XymxkFService;
import com.yicloud.trans.service.mysql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.*;

/**
 * Copyright (C).2020-2020.伊森科技
 *
 * @Author: chen
 * @Date: 2020/5/28 16:03
 * @FileName: RecipeInfoController
 * @Description: ss
 */
@RestController
@Transactional(rollbackFor = Exception.class)
@RequestMapping(value = "/recipeInfo")
public class RecipeInfoController {

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;
    @Autowired
    private RecipeDetailService recipeDetailService;
    @Autowired
    private RecipeInfoService recipeInfoService;
    @Autowired
    private PubStaffInfoService pubStaffInfoService;
    @Autowired
    private MzypmxkFService mzypmxkFService;
    @Autowired
    private XymxkFService xymxkFService;
    @Autowired
    private MidDrugService midDrugService;
    @Autowired
    private DrugRegionAliasService drugRegionAliasService;

    @PostMapping("/cleaning")
    @ResponseBody
    public String cleaning(@RequestParam Date startDate, @RequestParam Date endDate) throws Exception {
        RecipeInfo recipeInfo = new RecipeInfo();
        String strStartDate = DateUtil.format(startDate, "yyyy-MM-dd");
        String strEndDate = DateUtil.format(endDate, "yyyy-MM-dd");
        List<MzypmxkF> mzypmxkFList = new ArrayList<>(2048);
        System.out.println(StringUtils.hasLength("MzypmxkF_" + strStartDate + strEndDate));
        if (redisCacheTemplate.hasKey("MzypmxkF_" + strStartDate + strEndDate)) {
            Set<Serializable> MzypmxkFList = redisCacheTemplate.opsForSet().members("MzypmxkF_" + strStartDate + strEndDate);
            for (Serializable d : MzypmxkFList) {
                mzypmxkFList.add((MzypmxkF) d);
            }
        } else {
            mzypmxkFList = mzypmxkFService.list(new QueryWrapper<MzypmxkF>().lambda().ge(MzypmxkF::getSfrq, strStartDate).le(MzypmxkF::getSfrq, strEndDate));
            for (MzypmxkF mzypmxkF : mzypmxkFList) {
                redisCacheTemplate.opsForSet().add("MzypmxkF_" + strStartDate + strEndDate, mzypmxkF);
            }
        }
        String msg = null;
        for (MzypmxkF mzypmxkF : mzypmxkFList) {
            try {
                msg = "";
                if (mzypmxkF.getId().equals(3095229L)) {
                    System.out.println(mzypmxkF);
                }
                recipeInfo.setId(mzypmxkF.getId());
                recipeInfo.setVisId(mzypmxkF.getMzyskdkId() == null ? 1 : mzypmxkF.getMzyskdkId());
                recipeInfo.setChargesId(mzypmxkF.getSfbhId());
                recipeInfo.setRcpPosts(mzypmxkF.getTs());
                if (mzypmxkF.getCflx().equals("3")) {
                    recipeInfo.setRcpType(2);
                } else {
                    recipeInfo.setRcpType(1);
                }
                recipeInfo.setRcpDate(mzypmxkF.getRq());
                recipeInfo.setRecFee(mzypmxkF.getJe());
                recipeInfo.setRecipeId(mzypmxkF.getYcfid());
                if (Optional.ofNullable(mzypmxkF.getYcfid()).isPresent()) {
                    recipeInfo.setRcpRefundSign("1");
                }
                recipeInfo.setDispensingId(1L);
                recipeInfo.setRcpRefundSign("1");
                if (Optional.ofNullable(mzypmxkF.getJybz()).isPresent()) {
                    recipeInfo.setRcpBoilSign(mzypmxkF.getJybz().toString());
                }
                recipeInfo.setDispensingDate(mzypmxkF.getFyrq());
                if (mzypmxkF.getCflx().equals("3")) {
                    recipeInfo.setRcpStoreId(5002);
                } else {
                    recipeInfo.setRcpStoreId(5001);
                }

                PubStaffInfo pubStaffInfo = pubStaffInfoService.getOne(new QueryWrapper<PubStaffInfo>().lambda().eq(PubStaffInfo::getSffLoginNum, mzypmxkF.getYsh()));
                if (Optional.ofNullable(pubStaffInfo).isPresent()) {
                    recipeInfo.setSffId(pubStaffInfo.getId());
                } else {
                    msg = "医生工号为：" + mzypmxkF.getYsh() + "信息未维护在新系统.";
                }
                Jbxxk jbxxk = (Jbxxk) redisCacheTemplate.opsForHash().get("jbxxk", mzypmxkF.getZyh().toString());
                if (!Optional.ofNullable(jbxxk).isPresent()) {
                    msg = "人员信息不存在";
                    continue;
                }
                Patients patients = (Patients) redisCacheTemplate.opsForHash().get("patients", jbxxk.getSfzh());
                recipeInfo.setPatCardNum(patients.getPatCardNum());
                recipeInfo.setPatId(patients.getId());
                recipeInfo.setPatName(patients.getPatName());
                recipeInfo.setFeeId(patients.getFeeId());
                recipeInfo.setNatId(patients.getNatureId());
                recipeInfo.setHospitalId(330005L);
                recipeInfo.setDepId(5L);
                recipeInfoService.saveOrUpdate(recipeInfo);
                // 处方明细
                List<RecipeDetail> recipeDetailList = new ArrayList<>(64);
                DrugRegionAlias drugRegionAlias = new DrugRegionAlias();
                List<XymxkF> xymxkFList = xymxkFService.list(new QueryWrapper<XymxkF>().lambda().eq(XymxkF::getLsId, mzypmxkF.getId()));
                for (XymxkF xymxkF : xymxkFList) {
                    RecipeDetail recipeDetail = new RecipeDetail();
                    if (mzypmxkF.getCflx().equals("3")) {
                        xymxkF.setFsts(mzypmxkF.getTs());
                    }
                    String redisKey = "drugRegionAlias_" + xymxkF.getYph().toString() + xymxkF.getGgxh() + xymxkF.getCdId().toString();
                    if (!redisCacheTemplate.hasKey(redisKey)) {
                        YpCdWrapper ypCdWrapper = new YpCdWrapper();
                        try {
                            ypCdWrapper = (YpCdWrapper)redisCacheTemplate.opsForValue().get("ypCdWrapper_"+xymxkF.getYph().toString() + xymxkF.getGgxh() + xymxkF.getCdId().toString());
                            MidDrug midDrug = midDrugService.getOne(new QueryWrapper<MidDrug>().lambda().eq(MidDrug::getYph, xymxkF.getYph()).eq(MidDrug::getGgxh, xymxkF.getGgxh()).eq(MidDrug::getCdId, xymxkF.getCdId()));
                            drugRegionAlias = drugRegionAliasService.getById(midDrug.getDrugRegionId());
                            redisCacheTemplate.opsForValue().set(redisKey, drugRegionAlias);
                            redisCacheTemplate.opsForValue().set("drugRegionAlias_" + drugRegionAlias.getId().toString(), drugRegionAlias);
                        }catch (Exception e){
                            throw new Exception(ypCdWrapper.toString());
                        }
                    } else {
                        drugRegionAlias = (DrugRegionAlias) redisCacheTemplate.opsForValue().get(redisKey);
                    }

                    recipeDetail.setId(xymxkF.getId());
                    recipeDetail.setRecipeId(xymxkF.getLsId());
                    recipeDetail.setSpeId(drugRegionAlias.getSpeId());
                    recipeDetail.setManId(drugRegionAlias.getManId());
                    recipeDetail.setSubId(Long.valueOf(drugRegionAlias.getDrgType()));
                    recipeDetail.setRedNum(xymxkF.getSh());
                    recipeDetail.setDrgName(drugRegionAlias.getDrgName());
                    recipeDetail.setDrgPackingUnit(drugRegionAlias.getDrgPackingUnit());
                    recipeDetail.setRedPrice(xymxkF.getDj());
                    recipeDetail.setRedQuantity(xymxkF.getSl());
                    recipeDetail.setRedOnceDose(xymxkF.getDcfysl());
                    recipeDetail.setRedDoseUnit(xymxkF.getKfcfdw());
                    recipeDetail.setRedUseDay(xymxkF.getFsts());
                    recipeDetail.setHospitalId(330005L);
                    recipeDetailList.add(recipeDetail);
                }
                recipeDetailService.saveBatch(recipeDetailList);
            } catch (Exception exception) {
                throw new Exception(exception.getMessage()+msg + mzypmxkF.toString());
            }

        }
        return "papapapap@qq.com";
    }

}

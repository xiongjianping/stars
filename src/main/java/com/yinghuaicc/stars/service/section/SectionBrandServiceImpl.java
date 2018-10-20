package com.yinghuaicc.stars.service.section;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.date.LocalDateTimeUtils;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.contract.ContractMapper;
import com.yinghuaicc.stars.repository.mapper.dynamic.brand.BrandRateMapper;
import com.yinghuaicc.stars.repository.mapper.dynamic.fitted.FittedBrandMapper;
import com.yinghuaicc.stars.repository.mapper.dynamic.floor.FloorRateMapper;
import com.yinghuaicc.stars.repository.mapper.dynamic.project.ProjectRateMapper;
import com.yinghuaicc.stars.repository.mapper.dynamic.rentingRate.RentingRateMapper;
import com.yinghuaicc.stars.repository.mapper.region.RegionMapper;
import com.yinghuaicc.stars.repository.mapper.section.SectionBrandMapper;
import com.yinghuaicc.stars.repository.model.dynamic.brand.*;
import com.yinghuaicc.stars.repository.model.dynamic.floor.FloorRate;
import com.yinghuaicc.stars.repository.model.dynamic.project.ProjectRate;
import com.yinghuaicc.stars.repository.model.dynamic.standardkxd.StandardGuestSy;
import com.yinghuaicc.stars.repository.model.region.Floor;
import com.yinghuaicc.stars.repository.model.region.Project;
import com.yinghuaicc.stars.repository.model.section.SectionBrand;
import com.yinghuaicc.stars.service.dynamic.brand.dto.response.BrandRateListResponse;
import com.yinghuaicc.stars.service.dynamic.floor.dto.response.FloorRateListResponse;
import com.yinghuaicc.stars.service.dynamic.project.dto.response.ProjectRateListResponse;
import com.yinghuaicc.stars.service.section.dto.request.SectionBrandRequest;
import com.yinghuaicc.stars.service.section.dto.request.SectionBrandSyRequest;
import com.yinghuaicc.stars.service.section.dto.response.SectionBrandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/24.
 */
@Service
public class SectionBrandServiceImpl implements SectionBrandService {

    @Autowired
    SectionBrandMapper sectionBrandMapper;


    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;

    @Autowired
    FittedBrandMapper fittedBrandMapper;

    @Autowired
    RentingRateMapper rentingRateMapper;

    @Autowired
    RegionMapper regionMapper;

    @Autowired
    ExceptionUtil exceptionUtil;

    @Autowired
    ProjectRateMapper projectRateMapper;

    @Autowired
    FloorRateMapper floorRateMapper;

    @Autowired
    ContractMapper contractMapper;

    @Autowired
    BrandRateMapper brandRateMapper;

    /**
     * 新增业态级别
     * @param sectionBrand
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSectionBrand(SectionBrand sectionBrand) {
        if(sectionBrand.getProjectId() == null){
            throw exceptionUtil.throwCustomException("HELP_PROJEC_SAVE_001");
        }
        sectionBrandMapper.deleteSaveSection(sectionBrand);
        sectionBrand.setId(UuidUtil.randomUUID());
        sectionBrand.setCreateTime(LocalDateTime.now());
        sectionBrand.setCreateUser(aopResourceEmployeeBean.getName());
        sectionBrandMapper.saveSectionBrand(sectionBrand);
    }

    /**
     * 查看详情
     * @param id
     * @return
     */
    @Override
    public SectionBrand getSectionBrandById(String id) {
        return sectionBrandMapper.getSectionBrand(id);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSectionBrand(String id) {
        sectionBrandMapper.deleteSectionBrand(id);
    }

    /**
     * 查看列表
     * @param sectionBrandRequest
     * @param pageParam
     * @return
     */
    @Override
    public ResultPageList<SectionBrandResponse> getSectionBrandList(SectionBrandRequest sectionBrandRequest, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<SectionBrandResponse> result = sectionBrandMapper.getSectionBrandList(sectionBrandRequest);
        return new ResultPageList<SectionBrandResponse>()
                .setResultList(result)
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }

    @Override
    public SectionBrand getSectionBrandListById(SectionBrandSyRequest sectionBrandRequest) {
     //   String species = fittedBrandMapper.getFittedBrandSpeciesId(MapperFactoryUtil.mapperObject(sectionBrandRequest, FittedBrandSy.class));
      //  sectionBrandRequest.setSpeciesId(species);
        SectionBrand s = sectionBrandMapper.getSectionBrandListById(sectionBrandRequest);
        if(s == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_018");
        }
        return s;

    }

    @Override
    public String getWtSectionBrandListById(SectionBrandSyRequest sectionBrandRequest) {
        String a = "";
        SectionBrand s = sectionBrandMapper.getSectionBrandListById(sectionBrandRequest);
        if(s == null){
            a = "缺少业种区间值";
        }
        return a;
    }

    /**
     * 数据统计列表
     * @param tj
     * @return
     */
    @Override
    public List<TjList> getTjList(Tj tj) {
        List<TjList> lista = new ArrayList<>();
        TjList t= new TjList();
        Project p = regionMapper.findProjectById(tj.getProjectId());
        t.setProjectName(p.getName());
        t.setId(p.getId());
        Duration duration = Duration.between(LocalDateTimeUtils.StringDate(tj.getBeginTime()),LocalDateTimeUtils.StringDate(tj.getEndTime()));
        BigDecimal day = new BigDecimal(duration.toDays()+1); //时间差
        t.setDay(day.toString());
        StandardGuestSy s = new StandardGuestSy();
        s.setCreateTime(tj.getBeginTime());
        s.setModifyTime(tj.getEndTime());
        t.setKll("0"); //正常
        t.setXse("0"); //正常
        int xm = 0; //项目缺失数据天数
        int lc = 0; //楼层缺失数据天数
        int pp = 0; //品牌缺失数据天数
        List<String> list = rentingRateMapper.getSyDate(s);
        for(String b : list){
            s.setModifyUser(b);
            List<String> days = rentingRateMapper.getSyDateDay(s);
            for(String d : days){
                ProjectRate pr = new ProjectRate();
                pr.setProjectId(tj.getProjectId());
                pr.setEffectTime(d);
                List<ProjectRateListResponse> project = projectRateMapper.getProjectRateList(pr);
                if(project.size() == 0){
                    t.setKll("1"); //缺失
                    xm += 1;
                }

                List<String> l = contractMapper.getContractBycontractId(tj.getProjectId(),d);
                for (String n : l){
                    BrandRate brandRate = new BrandRate();
                    brandRate.setProjectId(tj.getProjectId());
                    brandRate.setEffectTime(d);
                    brandRate.setContractId(n);
                    List<BrandRateListResponse> brand = brandRateMapper.getBrandRateList(brandRate);
                    if(brand.size() == 0){
                        t.setKll("1"); //缺失
                        t.setXse("1"); //缺失
                        pp += 1;
                    }
                }
            }
        }


        List<Floor> f = regionMapper.findFloorByProjectId(tj.getProjectId());
        for (Floor a : f) {
            for (String b : list) {
                s.setModifyUser(b);
                List<String> days = rentingRateMapper.getSyDateDay(s);
                for (String d : days) {
                    FloorRate floorRate = new FloorRate();
                    floorRate.setProjectId(tj.getProjectId());
                    floorRate.setEffectTime(d);
                    floorRate.setFloorId(a.getId());
                    List<FloorRateListResponse> floor = floorRateMapper.getFloorRateList(floorRate);
                    if (floor.size() == 0) {
                        t.setKll("1"); //缺失
                        lc += 1;
                    }
                }
            }
        }

        t.setXmnum(String.valueOf(xm));
        t.setLcnum(String.valueOf(lc));
        t.setPpnum(String.valueOf(pp));
        lista.add(t);
        return lista;
    }

    @Override
    public TjDetail getTjDetail(Tj tj) {
        TjDetail t= new TjDetail();
        Project p = regionMapper.findProjectById(tj.getProjectId());
        t.setProjectName(p.getName());
        t.setId(p.getId());
        Duration duration = Duration.between(LocalDateTimeUtils.StringDate(tj.getBeginTime()),LocalDateTimeUtils.StringDate(tj.getEndTime()));
        BigDecimal day = new BigDecimal(duration.toDays()+1); //时间差
        t.setDay(day.toString());
        StandardGuestSy s = new StandardGuestSy();
        s.setCreateTime(tj.getBeginTime());
        s.setModifyTime(tj.getEndTime());
        t.setKll("0"); //正常
        t.setXse("0"); //正常
        int xm = 0; //项目缺失数据天数
        int lc = 0; //楼层缺失数据天数
        int pp = 0; //品牌缺失数据天数
        List<String> list = rentingRateMapper.getSyDate(s);
        List<String> projectlist = new ArrayList<>();
        List<FloorTj> floorlist = new ArrayList<>();//楼层缺失
        List<FloorTj> brandlist = new ArrayList<>();//品牌缺失
        for(String b : list){
            s.setModifyUser(b);
            List<String> days = rentingRateMapper.getSyDateDay(s);
            for(String d : days){
                ProjectRate pr = new ProjectRate();
                pr.setProjectId(tj.getProjectId());
                pr.setEffectTime(d);
                List<ProjectRateListResponse> project = projectRateMapper.getProjectRateList(pr);
                if(project.size() == 0){
                    t.setKll("1"); //缺失
                    xm += 1;
                    projectlist.add(d);
                }

                List<String> l = contractMapper.getContractBycontractId(tj.getProjectId(),d);
                for (String n : l){
                    BrandRate brandRate = new BrandRate();
                    brandRate.setProjectId(tj.getProjectId());
                    brandRate.setEffectTime(d);
                    brandRate.setContractId(n);
                    List<BrandRateListResponse> brand = brandRateMapper.getBrandRateList(brandRate);
                    if(brand.size() == 0){
                        t.setKll("1"); //缺失
                        t.setXse("1"); //缺失
                        pp += 1;
                        FloorTj f = new FloorTj();
                        f.setName(contractMapper.findBradeName(n));
                        f.setDay(d);
                        brandlist.add(f);
                    }
                }
            }
        }


        List<Floor> f = regionMapper.findFloorByProjectId(tj.getProjectId());
        for (Floor a : f) {
            for (String b : list) {
                s.setModifyUser(b);
                List<String> days = rentingRateMapper.getSyDateDay(s);
                for (String d : days) {
                    FloorRate floorRate = new FloorRate();
                    floorRate.setProjectId(tj.getProjectId());
                    floorRate.setEffectTime(d);
                    floorRate.setFloorId(a.getId());
                    List<FloorRateListResponse> floor = floorRateMapper.getFloorRateList(floorRate);
                    if (floor.size() == 0) {
                        t.setKll("1"); //缺失
                        lc += 1;
                        FloorTj fl = new FloorTj();
                        fl.setName(a.getName());
                        fl.setDay(d);
                        floorlist.add(fl);
                    }
                }
            }
        }

        t.setXmnum(String.valueOf(xm));
        t.setLcnum(String.valueOf(lc));
        t.setPpnum(String.valueOf(pp));
        t.setProject(projectlist);
        t.setFloor(floorlist);
        t.setBrand(brandlist);

        return t;
    }
}

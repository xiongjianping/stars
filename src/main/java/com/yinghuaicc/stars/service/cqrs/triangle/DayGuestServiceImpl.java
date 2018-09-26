package com.yinghuaicc.stars.service.cqrs.triangle;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.mapper.MapperFactoryUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.mapper.triangle.DayGuestMapper;
import com.yinghuaicc.stars.repository.model.triangle.DayGuest;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.DayGuestRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.DayGuestResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class DayGuestServiceImpl implements DayGuestService {

    @Autowired
    private DayGuestMapper dayGuestMapper;


    @Override
    public ResultPageList<DayGuestResponseDTO> findDayGuestByDayGuestCQRS(DayGuestRequestDTO dayGuestRequestDTO, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<DayGuestResponseDTO> result = MapperFactoryUtil.mapperList(dayGuestMapper.findDayGuestByDayGuestCQRS(dayGuestRequestDTO),DayGuestResponseDTO.class);

        return new ResultPageList<DayGuestResponseDTO>()
                .setResultList(result)
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }

    @Override
    public DayGuestResponseDTO findDayGuestByDayGuestByContractId(String contractId, LocalDateTime time) {
        return dayGuestMapper.findDayGuestByDayGuestByContractId(contractId,time);
    }


    @Override
    public void saveDayGuest(DayGuest dayGuest) {
        dayGuestMapper.saveDayGuest(dayGuest);
    }

    @Override
    public BigDecimal findPassengerFlowAll() {
        return dayGuestMapper.findPassengerFlowAll();
    }

    @Override
    public BigDecimal findSaleroomAll() {
        return dayGuestMapper.findSaleroomAll();
    }

    @Override
    public List<DayGuest> findDayGuestBynull() {
        return dayGuestMapper.findDayGuestBynull();
    }

    /**
     * 全国销售额7天
     * @return
     */
    @Override
    public List<BigDecimal> findDaySaleroomAll() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        int offset = 0;
//      以周一作为本周的第一天
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                offset--;
            case Calendar.SATURDAY:
                offset--;
            case Calendar.FRIDAY:
                offset--;
            case Calendar.THURSDAY:
                offset--;
            case Calendar.WEDNESDAY:
                offset--;
            case Calendar.TUESDAY:
                offset--;
            case Calendar.MONDAY:
                break;
            default:
                break;
        }
        String dtf = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(dtf);
        //起始日期
        cal.add(Calendar.DAY_OF_MONTH, offset);
        String begin = df.format(cal.getTime());
        System.out.println(df.format(cal.getTime()));
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        //结束日期
        String end = df.format(cal.getTime());
        System.out.println(df.format(cal.getTime()));
        List<BigDecimal> map = new ArrayList<>();
        List<String> day = dayGuestMapper.day(begin,end);
        day.forEach( p->{
            BigDecimal big = dayGuestMapper.findDaySaleroomAll(p);
            map.add(big == null ? new BigDecimal("0") : big );
        });

        return map;
    }

    @Override
    public List<BigDecimal> findDayPassengerFlowAll() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        int offset = 0;
//      以周一作为本周的第一天
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                offset--;
            case Calendar.SATURDAY:
                offset--;
            case Calendar.FRIDAY:
                offset--;
            case Calendar.THURSDAY:
                offset--;
            case Calendar.WEDNESDAY:
                offset--;
            case Calendar.TUESDAY:
                offset--;
            case Calendar.MONDAY:
                break;
            default:
                break;
        }
        String dtf = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(dtf);
        //起始日期
        cal.add(Calendar.DAY_OF_MONTH, offset);
        String begin = df.format(cal.getTime());
        System.out.println(df.format(cal.getTime()));
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        //结束日期
        String end = df.format(cal.getTime());
        System.out.println(df.format(cal.getTime()));
        List<BigDecimal> map = new ArrayList<>();
        List<String> day = dayGuestMapper.day(begin,end);
        day.forEach( p->{
            BigDecimal big = dayGuestMapper.findDayPassengerFlowAll(p);
            map.add(big == null ? new BigDecimal("0") : big );
        });

        return map;
    }

}

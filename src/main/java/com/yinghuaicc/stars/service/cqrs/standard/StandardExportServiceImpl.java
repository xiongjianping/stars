package com.yinghuaicc.stars.service.cqrs.standard;

import com.yinghuaicc.stars.common.utils.excel.ExcelData;
import com.yinghuaicc.stars.common.utils.excel.ExcelZjj;
import com.yinghuaicc.stars.repository.mapper.standard.StandardExportMapper;
import com.yinghuaicc.stars.service.cqrs.standard.dto.request.StandardExportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/7/29.
 */
@Service
public class StandardExportServiceImpl implements StandardExportService {
    @Autowired
    StandardExportMapper standardExportMapper;

    @Override
    public void getStandardExportList(HttpServletResponse response,StandardExportDTO standardExportDTO) throws Exception {
        List<ExcelData> list = new ArrayList<>();
        ExcelData data = new ExcelData();
        data.setName("品牌客销度");
        List<String> title = new ArrayList();
        title.add("签约id");
        title.add("项目名称");
        title.add("楼层");
        title.add("铺位号");
        title.add("品牌名称");
        title.add("业态名称");
        title.add("签约状态");
        title.add("毛利率");
        title.add("客单价");
        data.setTitles(title);
        List<List<Object>> rows = new ArrayList();
        standardExportMapper.getStandardExportList(standardExportDTO).forEach(p->{
            List<Object> row = new ArrayList();
            row.add(p.getContractId());
            row.add(p.getProjectName());
            row.add(p.getFloorName());
            row.add(p.getRoomName());
            row.add(p.getBrandName());
            row.add(p.getBusinessFormName());
            row.add(p.getState());
            rows.add(row);
        });
        data.setRows(rows);
        list.add(data);
        ExcelZjj excelZjj = new ExcelZjj();
        excelZjj.exportExcel(response,"标准三角形_客销度.xlsx",list);
    }
}

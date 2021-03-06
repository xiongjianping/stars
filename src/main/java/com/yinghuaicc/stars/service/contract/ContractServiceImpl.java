package com.yinghuaicc.stars.service.contract;

import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.repository.mapper.brand.BrandMapper;
import com.yinghuaicc.stars.repository.mapper.contract.ContractMapper;
import com.yinghuaicc.stars.repository.mapper.region.RegionMapper;
import com.yinghuaicc.stars.repository.model.brand.Brand;
import com.yinghuaicc.stars.repository.model.contract.Contract;
import com.yinghuaicc.stars.service.contract.dto.request.SaveContractRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/16 下午4:27
 * @Description: 签约
 * @Modified:
 */
@Service
public class ContractServiceImpl implements ContractService{

    @Autowired
    private ExceptionUtil exceptionUtil;

    @Autowired
    private ContractMapper contractMapper;

    @Autowired
    private RegionMapper regionMapper;

    @Autowired
    private BrandMapper brandMapper;

    /**
     *@Author:Fly Created in 2018/7/16 下午4:33
     *@Description: 添加签约信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveContract(SaveContractRequestDTO saveContractRequestDTO, String loginEmployeeId) {
        if(saveContractRequestDTO.getRoomId().size() > 1){
            throw exceptionUtil.throwCustomException("CONTRACT_SAVE_CONTRACT_010");
        }

        Integer count = contractMapper.countContractByBrandIdIdCount(saveContractRequestDTO.getBrandId(),saveContractRequestDTO.getProjectId());
        if(count != 0){
            throw exceptionUtil.throwCustomException("ROOM_CQRS_FIND_BY_PROJECT_002");
        }
        saveContractRequestDTO.getRoomId().stream().forEach(roomId -> {

            if (Objects.isNull(regionMapper.findRoomById(roomId))){

                throw exceptionUtil.throwCustomException("CONTRACT_SAVE_CONTRACT_005");
            }

           if (contractMapper.countContractByRoomId(roomId) > 0){

                throw exceptionUtil.throwCustomException("CONTRACT_SAVE_CONTRACT_006");
            }

            if (2 == regionMapper.findRoomById(roomId).getState()){

                throw exceptionUtil.throwCustomException("CONTRACT_SAVE_CONTRACT_007");
            }

            regionMapper.editRoom(
                    regionMapper.findRoomById(roomId)
                            .setState(3)
                            .setModifyTime(LocalDateTime.now())
                            .setModifyUser(loginEmployeeId));
        });

        saveContractRequestDTO.getRoomId().stream().forEach(str -> {
            String brandId = contractMapper.countContractByBrandIdId(saveContractRequestDTO.getBrandId(),saveContractRequestDTO.getProjectId());
            contractMapper.saveContract(
                    Stream.of(
                            new Contract()
                                    .setId(UuidUtil.randomUUID())
                                    .setProjectId(saveContractRequestDTO.getProjectId())
                                    .setFloorId(saveContractRequestDTO.getFloorId())
                                    .setRoomId(str)
                                    .setBrandId(saveContractRequestDTO.getBrandId())
                                    .setStatus(true)
                                    .setCreateUser(loginEmployeeId)
                                    .setModifyUser(loginEmployeeId)
                                    .setModifyTime(LocalDateTime.now())
                                    .setCreateTime(LocalDateTime.now()).setEffectTime(saveContractRequestDTO.getEffectTime()).setContractId(brandId == null ? UuidUtil.randomUUID() : brandId))
                            .collect(Collectors.toList()));
        });

        Brand brand = brandMapper.findBrandById(saveContractRequestDTO.getBrandId());
        brand.setState(1);
        brand.setModifyTime(LocalDateTime.now());
        brand.setModifyUser(loginEmployeeId);
        brandMapper.editBrand(brand);

    }

    /**
     *@Author:Fly Created in 2018/7/16 下午4:33
     *@Description: 解约
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void dispelContract(String id, String loginEmployeeId,String invalidTime) {

        List<Contract> contracts = contractMapper.findContractByIds(id);
        contracts.forEach(contract->{
            if (contract.isStatus()){

                contractMapper.editContractById(
                        contract
                                .setStatus(false)
                                .setModifyTime(LocalDateTime.now())
                                .setModifyUser(loginEmployeeId).setInvalidTime(invalidTime));


                regionMapper.editRoom(
                        regionMapper.findRoomById(contract.getRoomId())
                                .setState(1)
                                .setModifyTime(LocalDateTime.now())
                                .setModifyUser(loginEmployeeId));

                brandMapper.editBrand(
                        brandMapper.findBrandById(contract.getBrandId())
                                .setState(2)
                                .setModifyTime(LocalDateTime.now())
                                .setModifyUser(loginEmployeeId));
            }
        });
    }

    @Override
    public List<Contract> getContractAllBy() {
        return contractMapper.getContractAllBy();
    }
}

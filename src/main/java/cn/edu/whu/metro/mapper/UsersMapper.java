package cn.edu.whu.metro.mapper;

import cn.edu.whu.metro.dto.UserAgeDTO;
import cn.edu.whu.metro.dto.UserInfoDTO;
import cn.edu.whu.metro.dto.UserSexualDTO;
import cn.edu.whu.metro.dto.UsersProvinceDTO;
import cn.edu.whu.metro.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jdk.nashorn.internal.runtime.UserAccessorProperty;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author thomas
 * @since 2021-03-26
 */
public interface UsersMapper extends BaseMapper<Users> {

    List<UserInfoDTO> stationCountInBetween(@Param("station") String station,@Param("startTime") String startTime, @Param("endTime")String endTime);

    List<UserInfoDTO> stationCountOutBetween(@Param("station") String station,@Param("startTime") String startTime, @Param("endTime")String endTime);

    List<UserInfoDTO> countInBetween(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<UserInfoDTO> countOutBetween(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<UserSexualDTO> ratioOfSexual(@Param("station") String station);

    List<UserAgeDTO> ratioOfAge(@Param("station") String station);

    List<UsersProvinceDTO> queryUsersProvince();

}

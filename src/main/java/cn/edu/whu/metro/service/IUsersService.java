package cn.edu.whu.metro.service;

import cn.edu.whu.metro.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2021-03-26
 */
public interface IUsersService extends IService<Users> {

    Map<String,Float> ratioOfSexual(String station);

    Map<String,Float> ratioOfSexualI(String station);

    List<Map<Integer,Float>> ratioOfAge(String station);

    List<Map<Integer,Float>> ratioOfAgeI(String station);

}

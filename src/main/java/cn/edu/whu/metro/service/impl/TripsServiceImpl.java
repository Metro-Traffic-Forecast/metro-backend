package cn.edu.whu.metro.service.impl;

import cn.edu.whu.metro.entity.Trips;
import cn.edu.whu.metro.mapper.TripsMapper;
import cn.edu.whu.metro.service.ITripsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author thomas
 * @since 2021-03-26
 */
@Service
public class TripsServiceImpl extends ServiceImpl<TripsMapper, Trips> implements ITripsService {

}

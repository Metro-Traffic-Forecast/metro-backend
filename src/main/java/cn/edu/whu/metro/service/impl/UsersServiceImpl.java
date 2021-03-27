package cn.edu.whu.metro.service.impl;

import cn.edu.whu.metro.entity.Users;
import cn.edu.whu.metro.mapper.UsersMapper;
import cn.edu.whu.metro.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}

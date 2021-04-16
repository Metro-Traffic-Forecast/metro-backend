package cn.edu.whu.metro.service.impl;

import cn.edu.whu.metro.dto.UserAgeDTO;
import cn.edu.whu.metro.dto.UserSexualDTO;
import cn.edu.whu.metro.entity.Users;
import cn.edu.whu.metro.mapper.UsersMapper;
import cn.edu.whu.metro.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    UsersMapper usersMapper;

    @Override
    public Map<String, Float> ratioOfSexual(String station) {
        Map<String,Float> result = new HashMap<>();
        Integer sum = 0;
        double r = 0.0;

        try{
            List<UserSexualDTO> l= usersMapper.ratioOfSexual(station);
            for(UserSexualDTO userSexualDTO : l){
                sum = sum + userSexualDTO.getNumber();
            }
            for(int i=0;i<2;i++){
                UserSexualDTO t = l.get(i);
                if(t.getSex() == 0) {
                    result.put("男", (float) (t.getNumber()) / sum);
                }else {
                    result.put("女",(float) (t.getNumber()) / sum);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public Map<String, Float> ratioOfSexualI(String station) {
        Map<String,Float> result = new HashMap<>();
        Integer sum = 0;
        double r = 0.0;

        try{
            List<UserSexualDTO> l= usersMapper.ratioOfSexual(station);
            for(UserSexualDTO t : l){
                if(t.getSex() == 0) {
                    result.put("男", (float) (t.getNumber()));
                }else {
                    result.put("女",(float) (t.getNumber()));
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }

    private Calendar calendar = Calendar.getInstance();

    @Override
    public List<Map<Integer, Float>> ratioOfAge(String station) {
        Integer year = calendar.get(Calendar.YEAR);
        List<Map<Integer,Float>> result = new ArrayList<>();
        List<UserAgeDTO> l = usersMapper.ratioOfAge(station);
        Integer sum = 0;
        for(UserAgeDTO userAgeDTO : l){
            sum = sum + userAgeDTO.getNumber();
        }
        for (UserAgeDTO userAgeDTO : l){
            Map<Integer,Float> t = new HashMap<>();
            t.put(year-userAgeDTO.getBirth(),(float)userAgeDTO.getNumber()/sum);
            result.add(t);
        }
        return result;
    }

    @Override
    public List<Map<Integer, Float>> ratioOfAgeI(String station) {
        Integer year = calendar.get(Calendar.YEAR);
        List<Map<Integer,Float>> result = new ArrayList<>();
        List<UserAgeDTO> l = usersMapper.ratioOfAge(station);
        for (UserAgeDTO userAgeDTO : l){
            Map<Integer,Float> t = new HashMap<>();
            t.put(year-userAgeDTO.getBirth(),(float)userAgeDTO.getNumber());
            result.add(t);
        }
        return result;
    }


}

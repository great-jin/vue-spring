package xyz.greatinvincible.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.greatinvincible.entity.User;
import xyz.greatinvincible.mapper.UserMapper;
import xyz.greatinvincible.services.UserServices;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public User get(String code) {
        return userMapper.get(code);
    }

    @Override
    public User getUserByCode(User user) {
        return userMapper.getUserByCode(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.update(user);
    }
}

package xyz.greatinvincible.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import xyz.greatinvincible.entity.User;
import xyz.greatinvincible.mapper.UserMapper;
import xyz.greatinvincible.services.UserServices;

import java.util.List;

@Service
@CacheConfig(cacheNames = "users")
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable(key = "'list'")
    public List<User> list() {
        return userMapper.list();
    }

    /**
     * Redis缓存禁止空值时添加指令
     * unless="#result == null"
     */
    @Override
    @Cacheable(key = "#code")
    public User get(String code) {
        return userMapper.get(code);
    }

    /**
     * 新增用户后清除缓存中的列表数据
     */
    @Override
    @CacheEvict(key = "'list'")
    public int add(User user) {
        return userMapper.insert(user);
    }

    /**
     * 同时删除多个缓存
     * 同时清除列表和 get 用户的缓存
     */
    @Override
    @Caching(evict = {
            @CacheEvict(key = "'list'"),
            @CacheEvict(key = "#user.accountCode")
            }
    )
    public int update(User user) {
        return userMapper.update(user);
    }

    /**
     * 删除用户后清除缓存中的列表数据
     */
    @Override
    @Caching(evict = {
            @CacheEvict(key = "'list'"),
            @CacheEvict(key = "#user.accountCode")
    }
    )
    public int delete(String code) {
        return userMapper.delete(code);
    }
}

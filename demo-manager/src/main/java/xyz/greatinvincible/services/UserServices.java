package xyz.greatinvincible.services;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import xyz.greatinvincible.entity.User;

import java.util.List;

@CacheConfig(cacheNames = "users")
public interface UserServices {

    /**
     * Author:Budai 2022/1/6 9:15   
     * @return List<User>
     */
    List<User> list();

    /**
     * Author:Budai 2021/12/31 9:40
     * @param code
     * @return User
     */
    @Cacheable
    User get(String code);

    /**
     * Author:Budai 2021/12/31 9:40
     * @param user
     * @return User
     */
    User getUserByCode(User user);

    /**
     * Author:Budai 2021/12/31 10:06
     * 事务管理，异常时回滚
     * @param user
     * @return int
     */
    @Cacheable
    int updateUser(User user);

}

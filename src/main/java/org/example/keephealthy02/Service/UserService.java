package org.example.keephealthy02.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.keephealthy02.Entity.User;

public interface UserService extends IService<User> {
    User getuser(int id);
}

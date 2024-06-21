package org.example.keephealthy02.Service;

import io.swagger.models.auth.In;
import org.example.keephealthy02.Entity.User;

import java.util.List;

public interface UserService {
    User getuser(String id);
    public User selectById(String id);
    public void insert(User user);
    public void update(User user);
    Integer delete(String id);
    List<User> list();
}

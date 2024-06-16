package org.example.keephealthy02.Service;

import org.example.keephealthy02.Entity.User;

public interface UserService {
    User getuser(String id);
    public User selectById(String id);
    public void insert(User user);
    public void update(User user);
}

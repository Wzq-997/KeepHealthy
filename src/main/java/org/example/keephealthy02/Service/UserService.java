package org.example.keephealthy02.Service;

import org.example.keephealthy02.Entity.Result;
import org.example.keephealthy02.Entity.User;

public interface UserService {
    Result register(User user);
    Result login(String name, String password);
    Result getUserById(String id);
    Result updateUser(User user);
    Result deleteUser(String id);
}

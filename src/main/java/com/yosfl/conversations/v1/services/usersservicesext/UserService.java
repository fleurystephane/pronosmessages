package com.yosfl.conversations.v1.services.usersservicesext;

import com.yosfl.conversations.v1.entities.dtos.UserExt;
import com.yosfl.exceptions.ObjectNotFoundException;

public interface UserService {
    UserExt getUserExtByEmail(String email) throws ObjectNotFoundException;
}

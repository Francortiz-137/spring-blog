package franco.ortiz.blogspring.service;

import franco.ortiz.blogspring.entity.UserEntity;

import java.util.List;

public interface IUserService {
    List<UserEntity> findAll();
    UserEntity save(UserEntity user);
}

package franco.ortiz.blogspring.service.Impl;

import franco.ortiz.blogspring.entity.UserEntity;
import franco.ortiz.blogspring.respository.IUserRepo;
import franco.ortiz.blogspring.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final IUserRepo userRepo;

    public UserService(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepo.findAll();
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepo.save(user);
    }
}

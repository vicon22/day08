package edu.school21.service.services;

import edu.school21.service.models.User;
import edu.school21.service.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ComponentScan(basePackages = {"edu.school21.service"})
public class UsersServiceImpl implements UsersService{

    @Qualifier("usersRepositoryJdbcTemplateImpl")
    @Autowired
    UsersRepository usersRepository;

//    public UsersServiceImpl(UsersRepository usersRepository) {
//        this.usersRepository = usersRepository;
//    }

    @Override
    public String signUp(String email) {
        User user = new User(1, email, UUID.randomUUID().toString());
        usersRepository.save(user);

        return user.getPassword();
    }

    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
}

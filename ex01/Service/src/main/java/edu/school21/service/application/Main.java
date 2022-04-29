package edu.school21.service.application;

import edu.school21.service.models.User;
import edu.school21.service.repositories.UsersRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        usersRepository.save(new User(0, "email@email.com"));
        System.out.println(usersRepository.findAll());
        usersRepository.update(new User(1, "111@email.com"));
        System.out.println("==================");
        System.out.println(usersRepository.findByEmail("111@email.com"));

        System.out.println("==================");

        UsersRepository  usersRepository2 = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        usersRepository2.save(new User(1, "2222222@email.com"));
        usersRepository.update(new User(1, "333@email.com"));
        System.out.println(usersRepository2.findAll());
        System.out.println("==================");
        System.out.println(usersRepository.findByEmail("333@email.com"));

        System.out.println("==================");
    }
}

package edu.school21.service.application;

import edu.school21.service.config.ApplicationConfig;
import edu.school21.service.models.User;
import edu.school21.service.repositories.UsersRepository;
import edu.school21.service.repositories.UsersRepositoryJdbcImpl;
import edu.school21.service.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@Configuration
@ComponentScan(basePackages = {"edu.school21.service"})
public class Main {

    @Qualifier("usersRepositoryJdbcImpl")
    @Autowired
    UsersRepository usersRepositoryJdbc;

    @Qualifier("usersRepositoryJdbcTemplateImpl")
    @Autowired
    UsersRepository usersRepositoryTemplate;

    @Autowired
    UsersService usersService;

    public static void main(String[] args) {



        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        UsersRepository usersRepository = context.getBean(Main.class).usersRepositoryJdbc;
        usersRepository.save(new User(0, "11111@email.com","123"));
        System.out.println(usersRepository.findAll());

        System.out.println("==================");
        System.out.println(usersRepository.findByEmail("11111@email.com"));
        System.out.println("==================");
        System.out.println(usersRepository.findById(1L));

        System.out.println("==================");

        UsersRepository usersRepository2 = context.getBean(Main.class).usersRepositoryTemplate;

        usersRepository2.save(new User(1, "22222@email.com", "qwe"));
        System.out.println(usersRepository2.findAll());
        System.out.println("==================");

        UsersService usersService = context.getBean(Main.class).usersService;
        System.out.println(usersService.signUp("new@mail.ru"));
        System.out.println(usersService.signUp("2@mail.ru"));
        System.out.println(usersRepository2.findAll());
        System.out.println("==================");
        System.out.println(usersRepository2.findByEmail("11111@email.com"));

        System.out.println("==================");
        System.out.println(usersRepository.findById(1L));

    }
}

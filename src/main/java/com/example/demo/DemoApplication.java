package com.example.demo;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
//	@Bean
//	CommandLineRunner runner(UserRepository repository, MongoTemplate mongoTemplate){
//		return args-> {
//			String email = "atharvakafle2@gmail.com";
//			User user = new User(
//					"Atharva",
//					"atharva",
//					email,
//					"Nepal@123",
//					LocalDate.of(2022, Month.MARCH, 20)
//			);

//			usingMongoTemplateAndQuery(repository, mongoTemplate, email, user);
//			repository.findUserByEmail(email)
//					.ifPresentOrElse(u->{
//						System.out.println("User already exists");
//					},() -> {
//						repository.insert(user);
//						System.out.println("User Inserted");
//					});
//		};
//	}

	private void usingMongoTemplateAndQuery(UserRepository repository, MongoTemplate mongoTemplate, String email, User user) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));

		List<User> users = mongoTemplate.find(query, User.class);

		if(users.size()>1){
			throw new IllegalStateException("User already exists");
		}

		if(users.isEmpty()){
			repository.insert(user);
			System.out.println("User Inserted");
		}else{
			System.out.println("User already exists");
		}
	}
}

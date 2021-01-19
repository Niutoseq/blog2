package com.niutagodlewska.Blog2;

import com.niutagodlewska.Blog2.Models.Author;
import com.niutagodlewska.Blog2.Models.UserDTO;
import com.niutagodlewska.Blog2.Repositories.AuthorRepo;
import com.niutagodlewska.Blog2.Repositories.UserRepo;
import com.niutagodlewska.Blog2.Service.AuthorService;
import com.niutagodlewska.Blog2.Service.CommentService;
import com.niutagodlewska.Blog2.Service.ArticleService;
import com.niutagodlewska.Blog2.Service.TagService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.io.IOException;
import java.util.Locale;


@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepo.class)
public class Blog2Application {


	public static void main(String[] args) throws IOException {
		SpringApplication.run(Blog2Application.class, args);
	}

	@Bean
	public static CommandLineRunner loadData(AuthorService as, TagService ts, ArticleService ps, CommentService cs) {
		return (args) -> {
			as.saveAuthorData();
			ts.saveTagData();
			ps.savePostData();
			cs.saveCommentData();
		};
	}

	@Bean
	public static CommandLineRunner loadAdmin(UserRepo userRepo){
		return (args) -> {
			UserDTO user = new UserDTO();
			user.setUsername("postgres");
			user.setPassword("password");
			user.setRole("ADMIN");
			user.setActive(true);
			userRepo.save(user);
		};
	}

	@Bean
	public static CommandLineRunner loadUsers(UserRepo userRepo, AuthorRepo authorRepo){
		return (args) -> {
			for (Author author: authorRepo.findAll()){
				UserDTO user = new UserDTO();
				String name = author.getName().toLowerCase(Locale.ROOT);
				String[] data = name.split(" ");
				user.setUsername(data[0].charAt(0) + data[1]);
				user.setPassword("password");
				user.setRole("USER");
				user.setActive(true);
				userRepo.save(user);
			}
		};
	}


}

package com.cosine.cosgame;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cosine.cosgame.security.LoginInterceptor;

@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
		
		registration.addPathPatterns("/index","/dominion","/dominiongame","/dominionboard","/dominionlist","/dominionend",
				"/mafia","/mafiarules","/mafiagame", "/minigame/**", "/citadels", "/citadelscreategame", "/citadelsgame", 
				"/nothanks", "/nothankscreategame", "/nothanksgame", "/pokewhat", "/pokewhatcreategame", "/pokewhatgame",
				"/gravepsycho", "/gravepsychocreategame", "/gravepsychogame",
				"/onenight", "/onenightcreategame", "/onenightgame",
				"/zodiac", "/zodiaccreategame", "/zodiacgame",
				"/marshbros", "/marshbroscreategame", "/marshbrosgame",
				"/architect", "/architectcreategame", "/architectgame");
		//registration.excludePathPatterns("/login","/error","/view/css/**","/image/**","/js/**"); 
	}
}

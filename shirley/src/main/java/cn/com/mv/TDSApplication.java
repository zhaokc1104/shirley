package cn.com.mv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.com.mv.tds.configuration.TaskRegisterListener;

@SpringBootApplication
public class TDSApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(TDSApplication.class);
		application.addListeners(new TaskRegisterListener());
		application.run(args);
	}
}

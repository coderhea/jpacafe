package jpalatte.jpacafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpacafeApplication {

	public static void main(String[] args) {

		Hello hello  = new Hello();
		hello.setText("test");
		String text = hello.getText();
		System.out.printf("text :%s", text);
		SpringApplication.run(JpacafeApplication.class, args);
	}

}

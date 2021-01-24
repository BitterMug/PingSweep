package com.bitter.PingSweep;

import com.bitter.PingSweep.pingSweepCode.PingSweep;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
@ComponentScan({"com.bitter.PingSweep.*"})
public class PingSweepApplication {

	private static PingSweep pingSweep;

	public PingSweepApplication(PingSweep pingSweep) {
		PingSweepApplication.pingSweep = pingSweep;
	}

	public static void main(String[] args) {
		SpringApplication.run(PingSweepApplication.class, args);

		System.out.println("START");
		pingSweep.startPingSweep(1, 1);
	}

}

/*
package com.bitter.PingSweep.config;

import com.bitter.PingSweep.model.NameList;
import com.bitter.PingSweep.pingSweepCode.NameListRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class NameListConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            NameListRepository nameListRepository
    ) {
        return  args -> {
            NameList router = new NameList(
                    "192.168.0.1",
                    "router"
            );
            NameList bpc = new NameList(
                    "192.168.0.94",
                    "BPC"
            );
            nameListRepository.saveAll(
                    List.of(router, bpc)
            );
        };
    }
}
*/
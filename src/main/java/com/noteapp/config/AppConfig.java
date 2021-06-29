package com.noteapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({HibernateConf.class})
public class AppConfig {
}

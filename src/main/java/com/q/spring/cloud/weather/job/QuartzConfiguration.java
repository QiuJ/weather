package com.q.spring.cloud.weather.job;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {

    private static final int TIME = 1800; //更新频率

    // JobDetail
    /*@Bean
    public JobDetail weatherDataSyncJobJobDetail(){
        return JobBuilder.newJob(WeatherDataSyncJob.class).
                withIdentity("weatherDataSyncJob").storeDurably().build();
    }

    // Trigger
    @Bean
    public Trigger weatherDataSyncTrigger(){

        SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(TIME).repeatForever();
        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobJobDetail()).
                withIdentity("weatherDataSyncTrigger").withSchedule(schedBuilder).build();
    }*/

}

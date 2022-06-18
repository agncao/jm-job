package com.jm.job.core.common.utils;

import org.springframework.scheduling.support.CronSequenceGenerator;

import java.util.Date;

public abstract class CronParser {
    public static Date getNextDate(String cron){
        final CronSequenceGenerator g = new CronSequenceGenerator(cron);
        Date d = new Date();
        return g.next(d);
    }
}

package com.example.quartz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/9/5.
 */
@Service
public class QuartzServiceImpl implements QuartzService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //账单更新
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Scheduled(cron = "${example.bill.status.update}")
    public void updateBillStatus() {
        logger.error("--------1");
    }
}

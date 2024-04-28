package HongZe.springMVC.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskService {
	final Logger logger = LoggerFactory.getLogger(getClass());

	@Scheduled(initialDelay = 60000, fixedRate = 60000)
	public void checkSystemStatusEveryMinute() {
		logger.info("check system status every minute...");
	}

	@Scheduled(initialDelay = 30000, fixedRateString = "${disk.checkDiskSpace:30000}")
	public void checkDiskSpace() {
		logger.info("check disk space every 30 seconds...");
	}

	@Scheduled(cron = "${task.report:0 15 2 * * *}")
	public void cronDailyReport() {
		logger.info("cron daily report start...");
	}

	@Scheduled(cron = "${task.weekday:0 0 12 * * MON-FRI}")
	public void cronWeekdayReport() {
		logger.info("cron week report start...");
	}
}

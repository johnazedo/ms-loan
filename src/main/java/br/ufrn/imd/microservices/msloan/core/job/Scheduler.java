package br.ufrn.imd.microservices.msloan.core.job;

import br.ufrn.imd.microservices.msloan.core.log.Log;
import br.ufrn.imd.microservices.msloan.core.log.LogSender;
import br.ufrn.imd.microservices.msloan.payroll.service.PayrollService;
import org.springframework.boot.logging.LogLevel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private final PayrollService payrollService;
    private final LogSender logger;

    public Scheduler(PayrollService payrollService, LogSender logger) {
        this.payrollService = payrollService;
        this.logger = logger;
    }

    @Scheduled(cron = "* * * 5 * *")
    private void execute() {
        boolean result = payrollService.paymentDay();

        logger.send(new Log.LogBuilder()
                .setClazz(this.getClass().getName())
                .setMethod("paymentDay")
                .setContext("job")
                .setLevel(LogLevel.INFO)
                .setMessage("All payrolls are paid: " + result)
                .setIp("localhost")
                .build());
    }
}

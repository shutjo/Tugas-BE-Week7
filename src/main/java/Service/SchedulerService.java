package Service;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped

public class SchedulerService {
    private AtomicInteger generateFile = new AtomicInteger();
    private AtomicInteger sendEamil = new AtomicInteger();
    @Inject
    Mailer mailer;
    @Inject
    ReportService reportService;
    @Inject
    GeneratedReportPDFService generatedReportPDFService;
    @Scheduled(cron="0 0 0 ? * MON")
    public void filePDF(){
        generatedReportPDFService.reportPDF((generateFile.incrementAndGet()));
    }
    @Scheduled(cron="0 0 0 ? * MON")
    public void updateReportOneWeek(ScheduledExecution execution){
        reportService.updateReportScheduler();
        System.out.println(execution.getScheduledFireTime());
    }
    @Scheduled(cron = "20 0 08 1 1-12 ?")
    public void sendReport(){
        String email = "************@gmail.com";
        String subject = "Report Tomat";
        String message = "Report Invoice Tomat";
        int id = sendEamil.incrementAndGet();
        String fileName = "report" + id + ".pdf";
        String pathFile = "jasperReport/" + fileName;
        String contentType = "application/pdf";

        mailer.send(Mail.withText(email, subject, message)
                .addAttachment(fileName,
                        new File(pathFile), contentType)
        );
    }
}


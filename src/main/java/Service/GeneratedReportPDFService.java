package Service;

import net.sf.jasperreports.engine.*;

import org.jboss.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.util.UUID;
import java.sql.DriverManager;
@ApplicationScoped
public class GeneratedReportPDFService {
    private static final Logger log = Logger.getLogger(GeneratedReportPDFService.class);
    @Transactional
    public void reportPDF(Integer r) {
        log.info(" Report PDF");
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/dengklek",
                    "postgres",
                    "password"
            );
            String id = UUID.randomUUID().toString();
            String jasperReportTemplatePath = "jasperReport/report.jrxml";
            String fileName = "report" + id + ".pdf";
            String fileNameEtc = r.toString();
            String outputFile = "jasperReport/dengklek" + fileNameEtc + ".pdf";

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperReportTemplatePath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            JasperExportManager.exportReportToPdfFile(jasperPrint,outputFile);
            log.info("Generated PDF successfully");
        } catch (Exception e) {
            log.error("Generated PDF error");
        }
    }
}

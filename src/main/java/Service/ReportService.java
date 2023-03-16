package Service;

import Repository.ReportRepository;
import Model.ReportEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ReportService{
    @Inject
    ReportRepository emailController;
    public List<ReportEntity> listService() {
        return emailController.listReport();
    }
    public ReportEntity dataCreateAt() {
        return emailController.dataCreateAt();
    }
    public ReportEntity dataUpdateAt() {
        return emailController.dataUpdateAt();
    }
    public String createDate() {
        LocalDateTime dateCreate = LocalDateTime.now();
        return dateCreate.toString();
    }
    public String updateDate(){
        LocalDateTime dateUpdate = LocalDateTime.now();
        return dateUpdate.toString();
    }
    @Transactional
    public void updateReportScheduler(){
        ReportEntity reportEntity = emailController.dataUpdateAt();
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateTs = localDateTime.toString();
        if(reportEntity != null){
            ReportEntity newReport = new ReportEntity();
            newReport.createdAt = reportEntity.createdAt;
            newReport.updatedAt = dateTs;
            newReport.komoditas = reportEntity.komoditas;
            newReport.total = reportEntity.total + 500;
            newReport.persist();
        }
    }
}

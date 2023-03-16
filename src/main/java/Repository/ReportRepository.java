package Repository;

import Model.ReportEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
@ApplicationScoped
public class ReportRepository implements PanacheRepository<ReportEntity> {
    public List<ReportEntity> listReport() {
        return ReportEntity.listAll();
    }
    public ReportEntity dataCreateAt(){
        return find("order by updatedat").firstResult();
    }
    public ReportEntity dataUpdateAt(){
        return find("order by createdat").firstResult();
    }
}


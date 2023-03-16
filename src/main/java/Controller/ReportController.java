package Controller;

import Model.ReportEntity;
import Service.ReportService;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.List;

@Path("/report")
public class ReportController {
    @Inject
    ReportService reportService;

    @GET
    public List<ReportEntity> listEntity() {
        return reportService.listService();
    }

    @GET
    @Path("/create")
    public ReportEntity create() {
        return reportService.dataCreateAt();
    }

    @GET
    @Path("/update")
    public ReportEntity update() {
        return reportService.dataUpdateAt();
    }

    @POST
    @Transactional
    @Produces("application/json")
    @Consumes("application/json")
    public ReportEntity postReport(JsonObject body) {
        ReportEntity reportEntity = new ReportEntity();

        reportEntity.komoditas = body.getString("komoditas");
        reportEntity.createdAt = reportService.createDate();
        reportEntity.updatedAt = reportService.updateDate();
        reportEntity.total = Integer.valueOf(body.getString("total_Kg"));
        reportEntity.persist();
        return reportEntity;
    }
}


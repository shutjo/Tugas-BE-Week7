package Model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "report")
public class ReportEntity extends PanacheEntityBase {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    public String id;
    @Column(name = "komoditas")
    public String komoditas;
    @Column(name = "total")
    public Integer total;
    //@CreationTimestamp
    @Column(name = "createdAt") //  ,nullable = false)
    public String createdAt;
    //@UpdateTimestamp
    @Column(name = "updatedAt")
    public String updatedAt;

    public ReportEntity() {
    }
}
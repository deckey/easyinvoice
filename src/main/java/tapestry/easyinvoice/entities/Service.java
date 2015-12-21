package tapestry.easyinvoice.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Entity
@Table(name = "service")
public class Service implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "serviceId")
    private Integer serviceId;

    @Column(name = "serviceDescription")
    private String serviceDescription;

    @Column(name = "serviceAmount")
    private double serviceAmount;

    @ManyToOne
    @JoinColumn(name = "invoiceServiceId")
    private Invoice invoice;

    @Inject
    public Service() {
    }

    public Service(String serviceDescription, double serviceAmount) {
        this.serviceDescription = serviceDescription;
        this.serviceAmount = serviceAmount;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public double getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(double serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    
    

}

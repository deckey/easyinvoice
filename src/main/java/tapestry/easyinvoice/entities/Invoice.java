package tapestry.easyinvoice.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.annotations.Cascade;
import tapestry.easyinvoice.model.InvoiceStatus;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "invoiceId")
    private Integer invoiceId;

    @Column(name = "invoiceDescription")
    private String invoiceDescription;

    @Column(name = "invoiceIssueDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date invoiceIssueDate;

    @Column(name = "invoiceDueDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date invoiceDueDate;

    @Column(name = "invoiceStatus")
    @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus;

    @Column(name = "invoiceAmount")
    private double invoiceAmount;

    @Column(name = "invoiceCurrency")
    private String invoiceCurrency;

    @ManyToOne
    @JoinColumn(name = "invoiceClientId")
    private Client client;

    @Inject
    public Invoice() {
    }

    public Invoice(String invoiceDescription, Date invoiceIssueDate, Date invoiceDueDate, InvoiceStatus invoiceStatus, double invoiceAmount, String invoiceCurrency) {
        this.invoiceDescription = invoiceDescription;
        this.invoiceIssueDate = invoiceIssueDate;
        this.invoiceDueDate = invoiceDueDate;
        this.invoiceStatus = invoiceStatus;
        this.invoiceAmount = invoiceAmount;
        this.invoiceCurrency = invoiceCurrency;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceDescription() {
        return invoiceDescription;
    }

    public void setInvoiceDescription(String invoiceDescription) {
        this.invoiceDescription = invoiceDescription;
    }

    public Date getInvoiceIssueDate() {
        return invoiceIssueDate;
    }

    public void setInvoiceIssueDate(Date invoiceIssueDate) {
        this.invoiceIssueDate = invoiceIssueDate;
    }

    public Date getInvoiceDueDate() {
        return invoiceDueDate;
    }

    public void setInvoiceDueDate(Date invoiceDueDate) {
        this.invoiceDueDate = invoiceDueDate;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceCurrency() {
        return invoiceCurrency;
    }

    public void setInvoiceCurrency(String invoiceCurrency) {
        this.invoiceCurrency = invoiceCurrency;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return this.invoiceDescription;
    }
    
    

}

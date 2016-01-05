package tapestry.easyinvoice.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.easyinvoice.model.InvoiceCurrency;
import tapestry.easyinvoice.model.InvoiceStatus;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "invoiceId")
    private Integer invoiceId;

    @Column(name = "invoiceNumber")
    private String invoiceNumber;

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

    @Column(name = "invoiceCreationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceCreationDate;

    @Column(name = "invoiceAmount")
    private double invoiceAmount;

    @Column(name = "invoiceCurrency")
    private InvoiceCurrency invoiceCurrency;

    @ManyToOne
    @JoinColumn(name = "invoiceClientId")
    private Client client;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private Set<Service> services;

    @Inject
    public Invoice() {
        this.client = new Client();
        this.services = new HashSet<>();
        this.invoiceCreationDate = new Date();
    }

    public Invoice(String invoiceNumber, String invoiceDescription, Date invoiceIssueDate, Date invoiceDueDate, InvoiceStatus invoiceStatus, double invoiceAmount, InvoiceCurrency invoiceCurrency, Client client, Set<Service> services) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDescription = invoiceDescription;
        this.invoiceIssueDate = invoiceIssueDate;
        this.invoiceDueDate = invoiceDueDate;
        this.invoiceStatus = invoiceStatus;
        this.invoiceAmount = invoiceAmount;
        this.invoiceCurrency = invoiceCurrency;
        this.client = new Client();
        this.services = new HashSet<>();
        this.invoiceCreationDate = new Date();
    }

    public Invoice(String invoiceNumber, String invoiceDescription, Date invoiceIssueDate, Date invoiceDueDate, InvoiceCurrency invoiceCurrency) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDescription = invoiceDescription;
        this.invoiceIssueDate = invoiceIssueDate;
        this.invoiceDueDate = invoiceDueDate;
        this.invoiceCurrency = invoiceCurrency;
        this.client = new Client();
        this.services = new HashSet<>();
        this.invoiceCreationDate = new Date();
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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

    public Date getInvoiceCreationDate() {
        return invoiceCreationDate;
    }

    public void setInvoiceCreationDate(Date invoiceCreationDate) {
        this.invoiceCreationDate = invoiceCreationDate;
    }

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public InvoiceCurrency getInvoiceCurrency() {
        return invoiceCurrency;
    }

    public void setInvoiceCurrency(InvoiceCurrency invoiceCurrency) {
        this.invoiceCurrency = invoiceCurrency;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return this.invoiceDescription;
    }
}

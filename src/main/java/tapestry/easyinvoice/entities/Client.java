package tapestry.easyinvoice.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Entity
@Table(name = "client")
public class Client implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "clientId")
    private Integer clientId;

    @Column(name = "clientCompany")
    private String clientCompany;
    
    @Column(name = "clientContact")
    private String clientContact;

    @Column(name = "clientPhone")
    private String clientPhone;

    @Column(name = "clientEmail")
    private String clientEmail;

    @Column(name = "clientAmount")
    private double clientAmount;

    @Column(name = "clientIndustry")
    private String clientIndustry;

    @Column(name = "clientWebsite")
    private String clientWebsite;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Registration registration;
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Invoice> invoices;


    @Inject
    public Client() {
    }

    public Client(String clientCompany, String clientContact, String clientPhone, String clientEmail, String clientIndustry, String clientWebsite) {
        this.clientCompany = clientCompany;
        this.clientContact = clientContact;
        this.clientPhone = clientPhone;
        this.clientEmail = clientEmail;
        this.clientIndustry = clientIndustry;
        this.clientWebsite = clientWebsite;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientCompany() {
        return clientCompany;
    }

    public void setClientCompany(String clientCompany) {
        this.clientCompany = clientCompany;
    }

    public String getClientContact() {
        return clientContact;
    }

    public void setClientContact(String clientContact) {
        this.clientContact = clientContact;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public double getClientAmount() {
        return clientAmount;
    }

    public void setClientAmount(double clientAmount) {
        this.clientAmount = clientAmount;
    }

    public String getClientIndustry() {
        return clientIndustry;
    }

    public void setClientIndustry(String clientIndustry) {
        this.clientIndustry = clientIndustry;
    }

    public String getClientWebsite() {
        return clientWebsite;
    }

    public void setClientWebsite(String clientWebsite) {
        this.clientWebsite = clientWebsite;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public String toString() {
        return this.clientCompany +" company";
    }
    
    
    
}

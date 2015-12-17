package tapestry.easyinvoice.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Entity
@Table(name = "registration")
public class Registration implements Serializable{

    @Id
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name="gen",strategy = "foreign", parameters=@Parameter(name="property",value="client"))
    @Column(name = "registrationId")
    private Integer registrationId;

    @Column(name = "registrationAddress")
    private String registrationAddress;

    @Column(name = "registrationCity")
    private String registrationCity;

    @Column(name = "registrationCountry")
    private String registrationCountry;

    @Column(name = "registrationShipAddress")
    private String registrationShipAddress;

    @Column(name = "registrationShipCity")
    private String registrationShipCity;

    @Column(name = "registrationShipCountry")
    private String registrationShipCountry;

    @Column(name = "registrationNotes")
    private String registrationNotes;

    @OneToOne()
    @PrimaryKeyJoinColumn
    private Client client;

    @Inject
    public Registration() {
    }

    public Registration(String registrationAddress, String registrationCity, String registrationCountry, String registrationShipAddress, String registrationShipCity, String registrationShipCountry, String registrationNotes) {
        this.registrationAddress = registrationAddress;
        this.registrationCity = registrationCity;
        this.registrationCountry = registrationCountry;
        this.registrationShipAddress = registrationShipAddress;
        this.registrationShipCity = registrationShipCity;
        this.registrationShipCountry = registrationShipCountry;
        this.registrationNotes = registrationNotes;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    public String getRegistrationCity() {
        return registrationCity;
    }

    public void setRegistrationCity(String registrationCity) {
        this.registrationCity = registrationCity;
    }

    public String getRegistrationCountry() {
        return registrationCountry;
    }

    public void setRegistrationCountry(String registrationCountry) {
        this.registrationCountry = registrationCountry;
    }

    public String getRegistrationShipAddress() {
        return registrationShipAddress;
    }

    public void setRegistrationShipAddress(String registrationShipAddress) {
        this.registrationShipAddress = registrationShipAddress;
    }

    public String getRegistrationShipCity() {
        return registrationShipCity;
    }

    public void setRegistrationShipCity(String registrationShipCity) {
        this.registrationShipCity = registrationShipCity;
    }

    public String getRegistrationShipCountry() {
        return registrationShipCountry;
    }

    public void setRegistrationShipCountry(String registrationShipCountry) {
        this.registrationShipCountry = registrationShipCountry;
    }

    public String getRegistrationNotes() {
        return registrationNotes;
    }

    public void setRegistrationNotes(String registrationNotes) {
        this.registrationNotes = registrationNotes;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}

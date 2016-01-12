/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.pages.add;

import java.util.Date;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.easyinvoice.data.ClientDAO;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.entities.Registration;
import tapestry.easyinvoice.pages.Clients;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class AddClient {

    @Property
    private Client client;
    
    @Inject
    private ClientDAO clientDao;

    @Property
    private Registration registration;

    @Property
    @Validate("required")
    private String clientCompany;
    @Property
    private Integer clientTaxId;
    @Property
    @Validate("required")
    private String clientContact;
    @Property
    @Validate("required")
    private String clientEmail;
    @Property
    private String clientPhone;
    @Property
    private String clientWebsite;
    
    @InjectComponent("addClientForm")
    private Form form;

    @Property
    @Validate("required")
    private String registrationAddress;
    @Property
    @Validate("required")
    private String registrationCity;
    @Property
    @Validate("required")
    private String registrationCountry;

    @Property
    @Validate("required")
    private String registrationShipAddress;
    @Property
    @Validate("required")
    private String registrationShipCity;
    @Property
    @Validate("required")
    private String registrationShipCountry;
    @Property
    private String registrationNotes;
    @Property
    private String clientLogo;
    
    @Persist
    @Property
    private boolean separateShipTo;
    
    void onActivate(){
        client = new Client();
        registration = new Registration();
    }
    
    void onValidateFromAddClientForm(){
        
    }
    
    void onSubmitFromAddClientForm(){
    }
    @CommitAfter
    Object onSuccessFromAddClientForm(){
        client.setClientCompany(clientCompany);
        client.setClientTaxId(clientTaxId);
        client.setClientContact(clientContact);
        client.setClientEmail(clientEmail);
        client.setClientPhone(clientPhone);
        client.setClientWebsite(clientWebsite);
        client.setClientCreationDate(new Date());
        
        registration.setRegistrationAddress(registrationAddress);
        registration.setRegistrationShipAddress(registrationCountry);
        registration.setRegistrationCity(registrationCity);
        registration.setRegistrationShipCity(registrationCity);
        registration.setRegistrationCountry(registrationCountry);
        registration.setRegistrationShipCountry(registrationCountry);
        
        if(separateShipTo){
            registration.setRegistrationShipAddress(registrationShipAddress);
            registration.setRegistrationShipCity(registrationShipCity);
            registration.setRegistrationShipCountry(registrationShipCountry);
        }
        registration.setClient(client);
        client.setRegistration(registration);
        clientDao.addClient(client);
        return Clients.class;
    }
}

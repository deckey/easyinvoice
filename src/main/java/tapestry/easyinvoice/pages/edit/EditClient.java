/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.pages.edit;

import java.util.Set;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.easyinvoice.data.ClientDAO;
import tapestry.easyinvoice.data.DashboardDAO;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.entities.Invoice;
import tapestry.easyinvoice.entities.Registration;
import tapestry.easyinvoice.pages.view.ViewClient;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class EditClient {

    @Inject
    private DashboardDAO dashboardDao;

    @Inject
    private ComponentResources resources;

    @Property
    @Persist
    private Client client;
    @Property
    @Validate("required")
    private String clientCompany;
    @Inject
    private ClientDAO clientDao;
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

    @Property
    private Invoice invoice;

    @Property
    private Set<Invoice> invoices;

    @Property
    private Registration registration;

    @InjectComponent("editClientForm")
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
    @InjectPage
    private ViewClient viewClientPage;
    
    @Persist
    @Property
    private boolean separateShipTo;

    @CommitAfter
    Object onSuccessFromEditClientForm() {

        //UPDATE CLIENT FIELDS:
        client.setClientCompany(clientCompany);
        client.setClientTaxId(clientTaxId);
//        SET REGISTRATION DATA:
        Registration registration = client.getRegistration();
        registration.setRegistrationAddress(registrationAddress);
        registration.setRegistrationCity(registrationCity);
        registration.setRegistrationCountry(registrationCountry);
        if (separateShipTo) {
            registration.setRegistrationShipAddress(registrationShipAddress);
            registration.setRegistrationShipCity(registrationShipCity);
            registration.setRegistrationShipCountry(registrationShipCountry);
        }else{
            registration.setRegistrationShipAddress(registrationAddress);
            registration.setRegistrationShipCity(registrationCity);
            registration.setRegistrationShipCountry(registrationCountry);
        }
        registration.setRegistrationNotes(registrationNotes);

        client.setClientContact(clientContact);
        client.setClientEmail(clientEmail);
        client.setClientPhone(clientPhone);
        client.setClientWebsite(clientWebsite);

        System.out.println("UPDATING CLIENT.........");
        viewClientPage.set(client);
        return viewClientPage;
        
    }

//    PAGE ACTIVATION CONTEXT
    void onPageLoaded(Client client) {

    }

    public void set(Client client) {
        this.client = client;
    }

    void onActivate(Client client) {
        this.client = client;
        resources.discardPersistentFieldChanges();
        invoices = client.getInvoices();

        //FILL CLIENT FIELDS:
        clientCompany = client.getClientCompany();
        clientTaxId = client.getClientTaxId();
//        GET REGISTRATION DATA:
        Registration registration = client.getRegistration();
        registrationAddress = registration.getRegistrationAddress();
        registrationCity = registration.getRegistrationCity();
        registrationCountry = registration.getRegistrationCountry();
        registrationShipAddress = registration.getRegistrationShipAddress();
        registrationShipCity = registration.getRegistrationShipCity();
        registrationShipCountry = registration.getRegistrationShipCountry();
        registrationNotes = registration.getRegistrationNotes();

        clientContact = client.getClientContact();
        clientEmail = client.getClientEmail();
        clientPhone = client.getClientPhone();
        clientWebsite = client.getClientWebsite();

        if (!registrationAddress.equals(registrationShipAddress) || !registrationCity.equals(registrationShipCity) || !registrationCountry.equals(registrationShipCountry)) {
            separateShipTo = true;
        }

    }

    Client onPassivate() {
        return client;
    }
}

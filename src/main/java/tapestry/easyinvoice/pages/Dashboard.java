/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import tapestry.easyinvoice.data.ClientDAO;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.entities.Invoice;
import tapestry.easyinvoice.entities.Registration;
import tapestry.easyinvoice.model.InvoiceStatus;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class Dashboard {

    @Property
    private Client client;

    @Inject
    private ClientDAO clientDao;

    @Property
    private List<Client> clients;
    @Property
    private Invoice invoice;

    @Property
    private List<Registration> registrations;

    @Inject
    private Session dbs;

    void onActivate() {
        if (clients == null) {
            clients = new ArrayList<>();
        }
        clients = clientDao.getAllClients();
        registrations = dbs.createCriteria(Registration.class).list();
    }

    @CommitAfter
    void onCreateClient() {
        Client client1 = new Client("clientName", "clientPhone", "clientEmail", 450, "clientIndustry", "clientWebsite");
        Registration registration1 = new Registration("client1Address", "client1City", "client1 country", "client1shipping", "client1ShippingCity", "client1shipCountry", "some notes");

        client1.setRegistration(registration1);
        registration1.setClient(client1);

        clientDao.addClient(client1);

        Client client2 = new Client("clientName2", "clientPhone2", "clientEmail2", 450, "clientIndustry2", "clientWebsite2");
        Set<Invoice> invoices2 = new HashSet<>();

        Invoice invoice1 = new Invoice("description1", new Date(), new Date(), InvoiceStatus.Open, 400, "USD");
        Invoice invoice2 = new Invoice("description2", new Date(), new Date(), InvoiceStatus.Overdue, 200, "EUR");
        invoice1.setClient(client2);
        invoice2.setClient(client2);

        invoices2.add(invoice1);
        invoices2.add(invoice2);

        client2.setInvoices(invoices2);

        clientDao.addClient(client2);
    }
    
    

    public Set<Invoice> getClientInvoices() {
        Client client2 = clientDao.findClientById(2);
        if (client2 == null) {
            return null;
        }
        return client2.getInvoices();
    }
}

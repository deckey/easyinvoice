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
import tapestry.easyinvoice.entities.Service;
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

    @Property
    private List<Service> services;

    @Inject
    private Session dbs;

    void onActivate() {
        if (clients == null) {
            clients = new ArrayList<>();
        }
        clients = clientDao.getAllClients();
        registrations = dbs.createCriteria(Registration.class).list();
        services = dbs.createCriteria(Service.class).list();
    }

    @CommitAfter
    void onCreateClient() {
        Client client1 = new Client("clientCompany1", "clientContact1", "clientPhone", "clientEmail", "clientIndustry1", "clientWebsite");
        Registration registration1 = new Registration("client1Address", "client1City", "client1 country", "client1shipping", "client1ShippingCity", "client1shipCountry", "some notes");
        Set<Invoice> invoices1 = new HashSet<>();
        Set<Service> services1 = new HashSet<>();

        Invoice invoice1 = new Invoice("5", new Date(), new Date(), "USD");

        Service service1_1 = new Service("Service 1 description", 400);
        Service service1_2 = new Service("Service 1-2 description", 1000);

        service1_1.setInvoice(invoice1);
        service1_2.setInvoice(invoice1);
        services1.add(service1_1);
        services1.add(service1_2);

        invoice1.setServices(services1);
        invoice1.setClient(client1);
        invoices1.add(invoice1);

        registration1.setClient(client1);
        client1.setRegistration(registration1);
        client1.setInvoices(invoices1);

        clientDao.addClient(client1);
    }
}

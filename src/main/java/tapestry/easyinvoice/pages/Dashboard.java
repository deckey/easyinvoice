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
import tapestry.easyinvoice.data.DashboardDAO;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.entities.Invoice;
import tapestry.easyinvoice.entities.Registration;
import tapestry.easyinvoice.entities.Service;
import tapestry.easyinvoice.model.InvoiceCurrency;

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

    @Inject
    private DashboardDAO dashboardDao;

    @Property
    private Invoice invoice;
    @Property
    private Set<Invoice> invoices;

    @Property
    private Registration registration;

    @Property
    private List<Registration> registrations;

    @Property
    private Service service;

    @Property
    private Set<Service> services;

    @Inject
    private Session dbs;

    void onActivate() {
        if (clients == null) {
            clients = new ArrayList<>();
        }
        if (services == null) {
            services = new HashSet<>();
        }
        if (invoices == null) {
            invoices = new HashSet<>();
        }
        clients = clientDao.getAllClients();
        registrations = dbs.createCriteria(Registration.class).list();
    }

    public List<Invoice> getInvoiceList() {
        List<Invoice> invoiceList = new ArrayList<>();
        for (Client client : clients) {
            for (Invoice invoice : client.getInvoices()) {
                invoiceList.add(invoice);
            }
        }
        return invoiceList;
    }

    public List<Service> getServiceList() {
        List<Service> serviceList = new ArrayList<>();
        for (Client client : clients) {
            for (Invoice invoice : client.getInvoices()) {
                for (Service service : invoice.getServices()) {
                    serviceList.add(service);
                }
            }
        }
        return serviceList;
    }

    @CommitAfter
    void onCreateClient() {
        client = new Client("clientCompany1", "clientContact1", "clientPhone", "clientEmail", "clientIndustry1", "clientWebsite");
        registration = new Registration("client1Address", "client1City", "client1 country", "client1shipping", "client1ShippingCity", "client1shipCountry", "some notes");
        registration.setClient(client);
        clientDao.addClient(client);
    }

    @CommitAfter
    void onCreateInvoice() {
        invoice = new Invoice("12", "Invoice_Description3", new Date(), new Date(), InvoiceCurrency.EUR);
        invoices = new HashSet<>();
        invoices.add(invoice);
        dashboardDao.addInvoice(invoice);

    }

    @CommitAfter
    void onCreateService() {
        service = new Service("Service 1 description", 400);
        services = new HashSet<>();
        services.add(service);
        dashboardDao.addService(service);
    }

    @CommitAfter
    void onDeleteClient(Integer id) {
        clientDao.deleteClient(id);
    }
}

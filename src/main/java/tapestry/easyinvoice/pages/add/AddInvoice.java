/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.pages.add;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.easyinvoice.data.ClientDAO;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.entities.Invoice;
import tapestry.easyinvoice.entities.Service;
import tapestry.easyinvoice.model.InvoiceCurrency;
import tapestry.easyinvoice.model.InvoiceStatus;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class AddInvoice {

    //TODO: Continue CRUD on adding invoices
    //TODO: Logic for adding services 
    // Client service and properties/* Checkbox */
    @Property
    @Persist(PersistenceConstants.FLASH)
    private boolean checkboxValue;

    @Inject
    private ClientDAO clientDao;

    @Property
    private List<Client> clients;

    @InjectComponent("addInvoiceForm")
    private Form form;

    //Invoice fields:
    @Property
    @Validate("required")
    @Persist
    private Client client;

    @Property
    @Validate("required")
    @Persist
    private InvoiceCurrency currency;

    @Validate("required")
    @Property
    @Persist
    private Date dueDate;

    @Property
    @Persist
    private Date issueDate;

    @Property
    private Invoice invoice;

    @Property
    private Set<Invoice> invoices;

    @Property
    private String invoiceNumber;

    @Property
    private Service service;

    @Property
    @Validate("required")
    private String serviceDescription;

    @Property
    @Validate("required")
    private Integer serviceAmount;

    @Property
    @Persist
    private Set<Service> services;

    public InvoiceCurrency[] getCurrencies() {
        return InvoiceCurrency.values();
    }

    public String getInvoiceNumberFormat() {
        SimpleDateFormat invoiceNumFormat = new SimpleDateFormat("YYYY-MMdd");
        return invoiceNumFormat.format(issueDate);
    }

    void onPrepare() {

        if (clients == null) {
            clients = new ArrayList<>();
        }
        if (client == null) {
            client = clients.get(0);
        }
        if (services == null) {
            services = new HashSet<>();
        }
        if (service == null) {
            service = new Service();
        }
        if (invoices == null) {
            invoices = new HashSet<>();
        }
        if (invoice == null) {
            invoice = new Invoice();
        }
        System.out.println("CLIENT..." + client);
        System.out.println("INVOICES.." + invoices);
        System.out.println("INVOICE..." + invoice);
        System.out.println("SERVICES..." + services);
        System.out.println("SERVICE..." + service);
        issueDate = issueDate == null ? new Date() : issueDate;
        invoiceNumber = getInvoiceNumberFormat();

    }

    void onActivate() {
        clients = clientDao.getAllClients();

    }

    public Set<Service> getInvoiceServices() {
        return invoice.getServices();
    }

    void onClearServices() {
        services.clear();
    }

    //TODO: FIX ADDING SERVICES AND FINALIZE INVOICE CREATION
    @CommitAfter
    void onSubmitFromAddInvoiceForm() {
        Invoice invoice = new Invoice(invoiceNumber, issueDate, dueDate, currency.toString());
        Service service = new Service(serviceDescription, 30);
        service.setInvoice(invoice);
        services.add(service);
        
        invoice.setServices(services);
        invoice.setClient(client);
        invoices.add(invoice);
        
        client.setInvoices(invoices);
        service = new Service();
    }
    
    void onAddInvoice(){
        
    }

}

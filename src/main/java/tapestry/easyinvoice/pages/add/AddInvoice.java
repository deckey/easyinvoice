/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.pages.add;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.easyinvoice.data.ClientDAO;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.entities.Invoice;
import tapestry.easyinvoice.entities.Service;
import tapestry.easyinvoice.model.InvoiceCurrency;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class AddInvoice {

    //TODO: Continue CRUD on adding invoices
    //TODO: Logic for adding services 
    // Client service and properties/* Checkbox */
    @Property
    @Persist
    @Validate("required")
    private Client client;

    @Inject
    private ClientDAO clientDao;

    @Property
    private List<Client> clients;
//
//    @InjectComponent("addInvoiceForm")
//    private Form form;

    //Invoice fields:
    @Property
    @Persist
    @Validate("required")
    private InvoiceCurrency currency;

    @Property
    @Persist
    @Validate("required")
    private Date dueDate;

    @Property
    @Persist
    @Validate("required")
    private Date issueDate;

    @Property
    @Persist
    private Invoice invoice;

    @Property
    private Set<Invoice> invoices;

    @Property
    @Persist
    @Validate("required")
    private String invoiceDescription;

    @Property
    private String invoiceNumber;

    @Property
    @Validate("required")
    private String serviceDescription;

    @Property
    @Validate("required")
    private Integer serviceAmount;

    @Property
    private Service service;

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
        if (invoice == null) {
            invoice = new Invoice();
        }
        if (client == null) {
            client = clients.get(0);
        }
        issueDate = issueDate == null ? new Date() : issueDate;
        invoiceNumber = getInvoiceNumberFormat();
    }

    void onActivate() {
        clients = clientDao.getAllClients();
        service = new Service();
    }

    void onSubmitFromAddInvoiceForm() {

    }

    void onSubmitFromAddServiceForm() {
        System.out.println("SERVICE..." + service);
        service.setServiceAmount(serviceAmount);
        service.setServiceDescription(serviceDescription);
        service.setInvoice(invoice);
        services.add(service);
        invoice.setServices(services);
        
        
        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setInvoiceDescription(invoiceDescription);
        invoice.setInvoiceIssueDate(issueDate);
        invoice.setInvoiceDueDate(dueDate);
        invoice.setInvoiceCurrency(currency);
        invoice.setClient(client);

//        invoice.getServices().add(service);
//        System.out.println("INVOICE..."+invoice);
    }

    void onDeleteServices() {
        services.clear();
        invoice.setServices(services);
    }

//TODO: FIX ADDING SERVICES AND FINALIZE INVOICE CREATION
//    @CommitAfter
//    void onSubmitFromAddInvoiceForm() {
////        Service service = new Service(serviceDescription, 30);
////        service.setInvoice(invoice);
////
////        invoice.setClient(client);
////        invoices.add(invoice);
////
////        client.setInvoices(invoices);
////        service = new Service();
//    }
    void onAddInvoice() {
//        System.out.println("ADDING INVOICE...");
//        Invoice invoice1 = new Invoice(invoiceNumber, issueDate, dueDate, invoiceNumber);
//        System.out.println("INVOICE..." + invoice1);

    }

}

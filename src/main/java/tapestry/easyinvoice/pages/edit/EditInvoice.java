/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.pages.edit;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.PageLoaded;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import tapestry.easyinvoice.data.ClientDAO;
import tapestry.easyinvoice.data.DashboardDAO;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.entities.Invoice;
import tapestry.easyinvoice.entities.Service;
import tapestry.easyinvoice.model.InvoiceCurrency;
import tapestry.easyinvoice.model.InvoiceStatus;
import tapestry.easyinvoice.pages.view.ViewInvoice;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Import(library = "context:js/invoice.js")
public class EditInvoice {

    @InjectComponent("editInvoiceForm")
    private Form editInvoiceForm;
//
//    @InjectComponent("addServiceForm")
//    private Form addServiceForm;

    @Inject
    private AlertManager alertManager;

    @Inject
    private ClientDAO clientDao;

    @Property
    private List<Client> clients;

    @InjectComponent
    private Zone currencyZone;

    @Inject
    private DashboardDAO dashboardDao;

    @Property
    @Persist
    private Invoice invoice;

    @Property
    @Persist
    @Validate("required")
    private Client invoiceClient;

    @Property
    @Persist
    @Validate("required")
    private InvoiceCurrency invoiceCurrency;

    @Property
    @Validate("required")
    private String invoiceDescription;

    @Property
    @Validate("required")
    private String invoiceNumber;

    @Property
    @Validate("required")
    private Date invoiceIssueDate;

    @Property
    @Validate("required")
    private Date invoiceDueDate;

    @Property
    @Validate("required,min=1")
    private double serviceAmount;

    @Property
    @Validate("required")
    private String serviceDescription;

    @Property
    @Validate("required")
    private InvoiceStatus invoiceStatus;

    @InjectComponent
    private Zone invoiceStatusZone;

    @Inject
    private Request request;

    @Inject
    private AjaxResponseRenderer response;
    @Inject
    private ComponentResources resources;

    @Property
    private Service service;

    @InjectComponent
    private Zone serviceListZone;
//    @InjectComponent
//    private Zone serviceEntryZone;

    @Property
    private SortedSet<Service> tempServices;

    @InjectPage
    private ViewInvoice viewInvoicePage;

    public InvoiceCurrency[] getCurrencies() {
        return InvoiceCurrency.values();
    }

    public InvoiceStatus[] getStatuses() {
        return InvoiceStatus.values();
    }

    public Object onValueChangedFromInvoiceStatus(InvoiceStatus status) {
        invoiceStatus = status;
        invoice.setInvoiceStatus(invoiceStatus);
        return invoiceStatusZone.getBody();
    }

    public Object onValueChangedFromInvoiceClient(Client client) {
        invoiceClient = client;
        invoice.setClient(client);
        return invoiceStatusZone.getBody();
    }

    public void onValueChangedFromInvoiceCurrency(InvoiceCurrency currency) {
        invoiceCurrency = currency;
        invoice.setInvoiceCurrency(currency);
        response.addRender(currencyZone);
    }

    public boolean getCheckServices() {
        return tempServices.isEmpty();
    }

    public double getTotalAmount() {
        double amount = 0;
        for (Service service : tempServices) {
            amount += service.getServiceAmount();
        }
        return amount;
    }

    public String getInvoiceAmount() {
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return invoiceCurrency.getValue() + formatter.format(getTotalAmount());
    }

    public List<String> getClientCompanies() {
        List<String> clientList = new ArrayList<>();
        for (Client client : clients) {
            clientList.add(client.getClientCompany());
        }
        return clientList;
    }

    void onValidateFromEditInvoiceForm() {
        System.out.println("ADD INVOICE:VALIDATE...............");
        Set<Invoice> invoices = dashboardDao.getAllInvoices();

        if (invoiceDueDate.before(invoiceIssueDate)) {
            editInvoiceForm.recordError("Due date can not be before issue date!");
            return;
        }
        if (invoiceDueDate.before(new Date())) {
            invoiceStatus = InvoiceStatus.Overdue;
        }
        if (invoiceDueDate.after(new Date()) && invoiceStatus != InvoiceStatus.Closed) {
            invoiceStatus = InvoiceStatus.Open;
        }
        if (tempServices.isEmpty()) {
            editInvoiceForm.recordError("Invoice must have at least one service!");
        }
        System.out.println(".....................VALIDATED");
    }

    @CommitAfter
    Object onSuccessFromEditInvoiceForm() {
        System.out.println("ADD INVOICE:SUCCESS............................");
        //set invoice parameters:
        invoice.setInvoiceDescription(invoiceDescription);
        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setInvoiceIssueDate(invoiceIssueDate);
        invoice.setInvoiceDueDate(invoiceDueDate);
        invoice.setInvoiceCurrency(invoiceCurrency);
        invoice.setInvoiceAmount(getTotalAmount());
        invoice.setInvoiceCreationDate(new Date());
        invoice.setInvoiceStatus(invoiceStatus);

//      INVOICE <> CLIENT 
        invoice.setClient(invoiceClient);
        clientDao.findClientByCompanyName(invoiceClient.getClientCompany()).getInvoices().add(invoice);
//      INVOICE <> SERVICE
        for (Service service : tempServices) {
            service.setInvoice(invoice);
            invoice.getServices().add(service);
        }
        //add / update client:
        clientDao.updateClient(invoiceClient);
        //add invoice:
        dashboardDao.addInvoice(invoice);

        //reset invoice form
        tempServices.clear();
        System.out.println("..........................INVOICE UPDATED");

        viewInvoicePage.set(invoice);
        return viewInvoicePage;
    }

    @PageLoaded
    void onPageLoaded() {
        tempServices = new TreeSet<>();

    }

    public void set(Invoice invoice) {
        this.invoice = invoice;
    }

    void onActivate(Invoice invoice) {
        this.invoice = invoice;
        clients = clientDao.getAllClients();
        resources.discardPersistentFieldChanges();

//        GET INVOICE FIELDS
        invoiceDescription = invoice.getInvoiceDescription();
        invoiceNumber = invoice.getInvoiceNumber();
        invoiceIssueDate = invoice.getInvoiceIssueDate();
        invoiceDueDate = invoice.getInvoiceDueDate();
        invoiceClient = invoice.getClient();
        invoiceCurrency = invoice.getInvoiceCurrency();
        tempServices = new TreeSet<>(invoice.getServices());
        invoiceStatus = invoice.getInvoiceStatus();
    }

    Invoice onPassivate() {
        return invoice;
    }
}

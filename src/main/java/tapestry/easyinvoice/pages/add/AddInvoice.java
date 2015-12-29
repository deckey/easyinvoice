package tapestry.easyinvoice.pages.add;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.PageLoaded;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.OrderConstraintBuilder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import tapestry.easyinvoice.data.ClientDAO;
import tapestry.easyinvoice.data.DashboardDAO;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.entities.Invoice;
import tapestry.easyinvoice.entities.Service;
import tapestry.easyinvoice.model.InvoiceCurrency;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Import(library = "context:js/functions.js")
public class AddInvoice {

    @InjectComponent("addInvoiceForm")
    private Form addInvoiceForm;

    @InjectComponent("addServiceForm")
    private Form addServiceForm;

    @Inject
    private AlertManager alertManager;

    @Inject
    private ClientDAO clientDao;

    @Property
    private List<Client> clients;

    @Inject
    private DashboardDAO dashboardDao;

    @Property
    @Persist
    private Invoice invoice;

    @Property
    @Validate("required")
    private String invoiceClient;

    @Property
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

    @Inject
    private Request request;

    @Inject
    private AjaxResponseRenderer response;

    @Property
    private Service service;

    @InjectComponent
    private Zone serviceListZone;
    @InjectComponent
    private Zone serviceEntryZone;

    @Property
    private SortedSet<Service> tempServices;

    public InvoiceCurrency[] getCurrencies() {
        return InvoiceCurrency.values();
    }

    Object onValidateFromAddServiceForm() {
        //Add service to temporary list:
        tempServices.add(new Service(serviceDescription, serviceAmount, tempServices.size() + 1));
        if (!request.isXHR()) {
            return this;
        }
        //Reset input fields:
        serviceDescription = "";
        serviceAmount = 0;
        response.addRender(serviceEntryZone);
        return serviceListZone.getBody();
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
        return getTotalAmount() + invoiceCurrency.toString();
    }

    Object onClearServices() {
        tempServices.clear();
        //TODO: Clear services not clearing amount
        if (!request.isXHR()) {
            return this;
        }
        return serviceListZone.getBody();
    }

    public List<String> getClientCompanies() {
        List<String> clientList = new ArrayList<>();
        for (Client client : clients) {
            clientList.add(client.getClientCompany());
        }
        return clientList;
    }

    void onValidateFromAddInvoiceForm() {
        List<Invoice> invoices = dashboardDao.getAllInvoices();
        if (dashboardDao.checkIfInvoiceExists(clientDao.findClientByCompanyName(invoiceClient), invoiceNumber)) {
            addInvoiceForm.recordError("Invoice with number " + invoiceNumber + " for client " + invoiceClient + " already exists!");
            return;
        }
        if (invoiceDueDate.before(invoiceIssueDate)) {
            addInvoiceForm.recordError("Due date can not be before issue date!");
            return;
        }
        if (tempServices.isEmpty()) {
            addInvoiceForm.recordError("Invoice must have at least one service!");
        }
//        invoice.setInvoiceDescription(invoiceDescription);
//        invoice.setClient(clientDao.findClientByCompanyName(invoiceClient));
//        return ViewInvoice.class;
    }

    @PageLoaded
    void onPageLoaded() {
        tempServices = new TreeSet<>();
        clients = clientDao.getAllClients();
        if(invoiceCurrency==null){
            invoiceCurrency = InvoiceCurrency.USD;
        }
    }

    void onActivate() {
        invoice = new Invoice();
        if (invoiceIssueDate == null || invoiceIssueDate.toString() == "mm/dd/yyyy") {
            invoiceIssueDate = new Date();
        }
    }

    void onSubmitFromAddInvoiceForm() {
    }

//    @CommitAfter
    void onCreateInvoice() {
        invoice.setInvoiceDescription(invoiceDescription);
        System.out.println("INVOICE DESCRIPTION......." + invoice.getInvoiceDescription());
//        
//
////        INVOICE <> CLIENT 
//        invoice.setClient(clientDao.findClientByCompanyName(invoiceClient));
//        clientDao.findClientByCompanyName(invoiceClient).getInvoices().add(invoice);
//
////        INVOICE <> SERVICE
//        for (Service service : tempServices) {
//            service.setInvoice(invoice);
//            invoice.getServices().add(service);
//        }
//
//        clientDao.addClient(clientDao.findClientByCompanyName(invoiceClient));
//        dashboardDao.addInvoice(invoice);
    }
}

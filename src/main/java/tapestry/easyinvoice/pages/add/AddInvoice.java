package tapestry.easyinvoice.pages.add;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
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
    private InvoiceStatus invoiceStatus = InvoiceStatus.Open;

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

    @InjectPage
    private ViewInvoice viewInvoicePage;

    public InvoiceCurrency[] getCurrencies() {
        return InvoiceCurrency.values();
    }

    public Object onValueChangedFromInvoiceCurrency(InvoiceCurrency currency) {
        this.invoiceCurrency = currency;
        return currencyZone.getBody();
    }

    public Object onValueChangedFromInvoiceClient(Client client) {
        this.invoiceClient = client;
        System.out.println("INVOICE CLIENT.........." + invoiceClient.getClientCompany());
        return currencyZone.getBody();
    }

    void onValidateFromAddServiceForm() {
        //Add service to temporary list:
        tempServices.add(new Service(serviceDescription, serviceAmount, tempServices.size() + 1));
    }

    Object onSuccessFromAddServiceForm() {
        if (!request.isXHR()) {
            return null;
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
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return invoiceCurrency.getValue() + formatter.format(getTotalAmount());
    }

    Object onClearServices() {
        tempServices.clear();
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
        Set<Invoice> invoices = dashboardDao.getAllInvoices();
        System.out.println("INVOICE CLIENT.........." + invoiceClient);
        System.out.println("INVOICE NUMBER.........." + invoiceNumber);
        if (dashboardDao.checkIfInvoiceExists(invoiceClient.getClientCompany(), invoiceNumber)) {
            addInvoiceForm.recordError("Invoice with number " + invoiceNumber + " for client " + invoiceClient + " already exists!");
            return;
        }
        if (invoiceDueDate.before(invoiceIssueDate)) {
            addInvoiceForm.recordError("Due date can not be before issue date!");
            return;
        }
        if (invoiceDueDate.before(new Date())) {
            invoiceStatus = InvoiceStatus.Overdue;
        }
        if (tempServices.isEmpty()) {
            addInvoiceForm.recordError("Invoice must have at least one service!");
        }
    }

    @CommitAfter
    Object onSuccessFromAddInvoiceForm() {
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
        System.out.println("..........................INVOICE CREATED");

        viewInvoicePage.set(invoice);
        return viewInvoicePage;
    }

    @PageLoaded
    void onPageLoaded() {
        tempServices = new TreeSet<>();

    }

    void onActivate() {
        invoice = new Invoice();
        if (invoiceIssueDate == null || invoiceIssueDate.toString() == "mm/dd/yyyy") {
            invoiceIssueDate = new Date();
        }
        clients = clientDao.getAllClients();
        if (invoiceClient == null) {
            invoiceClient = clients.get(0);
        }
    }
}

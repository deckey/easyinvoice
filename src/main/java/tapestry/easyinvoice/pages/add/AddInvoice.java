package tapestry.easyinvoice.pages.add;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.beaneditor.PropertyModel;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import tapestry.easyinvoice.data.ClientDAO;
import tapestry.easyinvoice.data.DashboardDAO;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.entities.Invoice;
import tapestry.easyinvoice.entities.Service;
import tapestry.easyinvoice.model.InvoiceCurrency;
import tapestry.easyinvoice.pages.view.ViewInvoice;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Import(library = "context:js/functions.js")
public class AddInvoice {

    @InjectComponent
    private Zone invoicePreviewZone;

    @Inject
    private AjaxResponseRenderer ajaxResponse;
    
    @Inject
    private Request request;

    @Inject
    private AlertManager alertManager;

    //TODO: Continue CRUD on adding invoices
    //TODO: Logic for adding services 
    // Client service and properties/* Checkbox */
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private ClientDAO clientDao;

    @Property
    private List<Client> clients;

    @Inject
    private DashboardDAO dashboardDao;

    @InjectComponent("addInvoiceForm")
    private Form invoiceForm;

    @InjectComponent("addServiceForm")
    private Form serviceForm;

    @Property
    @Persist
    private Client invoiceClient;

    @Property
    @Persist
    private String invoiceDescription;

    @Property
    @Persist
    private String invoiceNumber;

    @Property
    @Validate("required")
    @Persist
    private InvoiceCurrency invoiceCurrency;

    @Property
    @Persist
    private Date invoiceDueDate;

    @Property
    @Persist
    private Date invoiceIssueDate;

    @Property
    private Set<Invoice> invoices;

    @Property
    @Persist
    private Invoice invoice;

    @Inject
    private Messages messages;

    @Property
    @Persist
    private SortedSet<Service> services;

    @Property
    private BeanModel<Service> servicesGrid;

    @Property
    private Service service;

    @Property
    private double serviceAmount;

    @Property
    private String serviceDescription;

    @InjectPage
    private ViewInvoice viewInvoicePage;

    public InvoiceCurrency[] getCurrencies() {
        return InvoiceCurrency.values();
    }

//    UTILITIES.................................
    public String getFormatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, YYYY");
        return (date != null) ? dateFormat.format(date) : "";
    }

    public String getFormatAmount(double amount) {
        return String.format("%.2f", amount);
    }

    public Set<Service> getInvoiceServices() {
        return (invoice.getServices() == null) ? new TreeSet<>() : invoice.getServices();
    }

    public String formatInvoiceNumber(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MMdd");
        return sdf.format(date);
    }

//    INVOICE ..............................
    void onSubmitFromAddInvoiceForm() {
        invoice.setInvoiceDescription(invoiceDescription);
        invoice.setInvoiceNumber(invoiceNumber);
        if (invoiceIssueDate != null && invoiceDueDate != null) {
            if (invoiceIssueDate.after(invoiceDueDate)) {
                invoiceForm.recordError("Due date can not be before issue date!");
                return;
            }
        }
        invoice.setInvoiceIssueDate(invoiceIssueDate);
        invoice.setInvoiceDueDate(invoiceDueDate);
        invoice.setInvoiceCurrency(invoiceCurrency);
        invoice.setClient(invoiceClient);
    }

    void onResetInvoiceForm() {
        invoiceClient = null;
        invoiceNumber = "";
        invoiceCurrency = getCurrencies()[0];
        invoiceDescription = "";
        invoiceDueDate = null;
        invoiceIssueDate = null;
        invoice = new Invoice();
    }

    public boolean validateInvoice() {
        String msg = "Invoice not valid......";
        if (invoice.getInvoiceDescription() == null || invoice.getInvoiceDescription() == "") {
            alertManager.alert(Duration.TRANSIENT, Severity.ERROR, msg + "Description can not be blank!");
            return false;
        }
        if (invoice.getInvoiceNumber() == null || invoice.getInvoiceNumber() == "") {
            alertManager.alert(Duration.TRANSIENT, Severity.ERROR, msg + "Invoice number can not be zero!");
            return false;
        }
        if (invoice.getInvoiceDueDate() == null || invoice.getInvoiceDueDate() == null) {
            alertManager.alert(Duration.TRANSIENT, Severity.ERROR, msg + "Both dates are required!");
        }
        if (invoice.getClient() == null) {
            alertManager.alert(Duration.TRANSIENT, Severity.ERROR, msg + "Client must be set!");
            return false;
        }
        if (invoice.getServices() == null || invoice.getServices().isEmpty()) {
            alertManager.alert(Duration.TRANSIENT, Severity.ERROR, msg + "Invoice must have at least one service!");
        }
//        SERVER VALIDATION FOR DUPLICATE INVOICES....
        boolean exists = dashboardDao.checkIfInvoiceExists(invoice.getClient(), invoice.getInvoiceNumber());
        if (exists) {
            alertManager.alert(Duration.TRANSIENT, Severity.ERROR, "Invoice with the same number for " + invoice.getClient() + " client, already exists!");
        }
        return true;
    }

    @CommitAfter
    Object onPreviewInvoice() {
        if (!validateInvoice()) {
            return this;
        }

        for (Service service : invoice.getServices()) {
            dashboardDao.addService(service);
        }
        invoice.setServices(services);
        dashboardDao.addInvoice(invoice);
        viewInvoicePage.set(invoice);

        //TODO: RESET INVOICE PARAMETERS
//        invoiceClient = null;
//        invoiceNumber = "";
//        invoiceCurrency = getCurrencies()[0];
//        invoiceDescription = "";
//        invoiceDueDate = null;
//        invoiceIssueDate = null;
//        invoice = new Invoice();
        return viewInvoicePage;
    }

//    SERVICES...................
    Object onSubmitFromAddServiceForm() {
        //TODO: TROUBLESHOOT UPDATE VIA AJAX ZONE
//        service = new Service();
        if (validateServiceInput()) {
            service.setServiceDescription(serviceDescription);
            service.setServiceAmount(serviceAmount);
            service.setServiceNumber(services.size() + 1);
            service.setInvoice(invoice);
            services.add(service);
        }
        invoice.setServices(services);
        invoice.setInvoiceAmount(getServiceTotal());
        return request.isXHR() ? invoicePreviewZone.getBody() : null;
//        ajaxResponse.addRender("invoicePreviewZone", invoicePreviewZone);
    }

    void onClearServices() {
        services = new TreeSet<>();
        invoice.setServices(services);
        invoice.setInvoiceAmount(getServiceTotal());
    }

//    FORM VALIDATION
    public boolean validateServiceInput() {
        if (serviceDescription == null || serviceDescription == "") {
            serviceForm.recordError("Enter service description!");
            return false;
        }
        if (serviceAmount == 0) {
            serviceForm.recordError("Service amount can't be zero!");
            return false;
        }
        return true;
    }

    public double getServiceTotal() {
        double total = 0;
        for (Service service : services) {
            total += service.getServiceAmount();
        }
        return total;
    }

//    PAGE ACTIVATION CONTEXT..............................
    void setupRender() {
        servicesGrid = beanModelSource.createDisplayModel(Service.class, messages);
        List<String> propertyNames = servicesGrid.getPropertyNames();
        for (String propertyName : propertyNames) {
            PropertyModel propertyModel = servicesGrid.get(propertyName);
            propertyModel.sortable(false);
        }
        servicesGrid.include("serviceDescription", "serviceAmount");
        servicesGrid.get("serviceDescription").label("Service description");
        servicesGrid.get("serviceAmount").label("Amount");

    }

    void onActivate() {
        clients = clientDao.getAllClients();
        if (services == null) {
            services = new TreeSet<>();
        }
        if(service==null){
            service = new Service();
        }
        if (invoices == null) {
            invoices = new HashSet<>();
        }
        if (invoice == null) {
            invoice = new Invoice();
        }
        if (invoice.getInvoiceCurrency() == null) {
            invoice.setInvoiceCurrency(getCurrencies()[0]);
        }
        if (invoiceIssueDate == null) {
            invoiceIssueDate = new Date();
        }
    }
}

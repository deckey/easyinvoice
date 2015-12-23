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
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.beaneditor.PropertyModel;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.HibernatePersistenceConstants;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
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
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private ClientDAO clientDao;

    @Property
    private List<Client> clients;

    @InjectComponent("addInvoiceForm")
    private Form invoiceForm;

    @InjectComponent("addServiceForm")
    private Form serviceForm;

    @Property
    @Validate("required")
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

    public InvoiceCurrency[] getCurrencies() {
        return InvoiceCurrency.values();
    }

    void setupRender() {
        servicesGrid = beanModelSource.createDisplayModel(Service.class, messages);
        List<String> propertyNames = servicesGrid.getPropertyNames();
        for (String propertyName : propertyNames) {
            PropertyModel propertyModel = servicesGrid.get(propertyName);
            propertyModel.sortable(false);
        }
        servicesGrid.include("serviceId", "serviceDescription", "serviceAmount");
        servicesGrid.get("serviceId").label("#");
        servicesGrid.get("serviceDescription").label("Service description");
        servicesGrid.get("serviceAmount").label("Amount");

    }

    void onActivate() {
        clients = clientDao.getAllClients();
        if (services == null) {
            services = new TreeSet<>();
        }
        if (invoices == null) {
            invoices = new HashSet<>();
        }
        if (invoiceClient == null) {
            invoiceClient = clients.get(0);
        }
        if (invoice == null) {
            invoice = new Invoice();
        }
        if (invoice.getInvoiceCurrency() == null) {
            invoice.setInvoiceCurrency(getCurrencies()[0]);
        }
    }

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

    void onClearServices() {
        services = new TreeSet<>();
        invoice.setServices(services);
        invoice.setInvoiceAmount(getServiceTotal());
    }

    void onSubmitFromAddServiceForm() {
        service = new Service();
        System.out.println("SUBMITTING...");
        if (validateInput()) {
            service.setServiceDescription(serviceDescription);
            service.setServiceAmount(serviceAmount);
            service.setServiceId(services.size() + 1);
            services.add(service);
        }
        invoice.setServices(services);
        invoice.setInvoiceAmount(getServiceTotal());
    }

    void onSubmitFromAddInvoiceForm() {
        invoice.setInvoiceDescription(invoiceDescription);
        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setInvoiceIssueDate(invoiceIssueDate);
        invoice.setInvoiceDueDate(invoiceDueDate);

        invoice.setInvoiceCurrency(invoiceCurrency);
        invoice.setClient(invoiceClient);
    }

    void onClearInvoiceForm() {
        invoiceClient = clients.get(0);
        invoiceNumber = "";
        invoiceCurrency = getCurrencies()[0];
        invoiceDescription = "";
        invoiceDueDate = null;
        invoiceIssueDate = null;
        invoice = new Invoice();

    }

//    FORM VALIDATION
    public boolean validateInput() {
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
}

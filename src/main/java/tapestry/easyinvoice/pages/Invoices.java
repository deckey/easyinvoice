/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.pages;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.Request;
import tapestry.easyinvoice.data.ClientDAO;
import tapestry.easyinvoice.data.DashboardDAO;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.entities.Invoice;
import tapestry.easyinvoice.model.InvoiceCurrency;
import tapestry.easyinvoice.model.InvoiceStatus;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Import(library = {"context:js/invoices.js"})
public class Invoices {

    @Inject
    private DashboardDAO dashboardDao;

    @Inject
    private ClientDAO clientDao;

    @Property
    private Set<Invoice> invoices;

    @Property
    private List<Client> clients;

    @InjectComponent
    private Zone displayInvoicesZone;

    @Property
    private Invoice invoice;

    @Property
    @Validate("required")
    private InvoiceCurrency invoiceCurrency;

    @Property
    private List<Invoice> invoiceList;

    @InjectComponent
    private Zone invoiceListZone;

    @Property
    private InvoiceStatus invoiceStatus;

    @Property
    private BeanModel<Invoice> invoiceGridModel;

    @Inject
    private Messages messages;
    @Inject
    private BeanModelSource beanModelSource;

    @Inject
    private Request request;

    @Property
    private List<Invoice> sortedInvoices;

    public InvoiceStatus[] getStatuses() {
        return InvoiceStatus.values();
    }

    public InvoiceCurrency[] getCurrencies() {
        return InvoiceCurrency.values();
    }

    public DecimalFormat getNumberFormat() {
        return new DecimalFormat("0");
    }
    
    public String getInvoiceCreationDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD, YYYY");
        return dateFormat.format(invoice.getInvoiceCreationDate());
    }

    public Object onDisplayInvoices(InvoiceStatus status) {
        invoiceList = getFilteredInvoices(status);
        Collections.sort(invoiceList);
        return displayInvoicesZone.getBody();
    }

    public int getInvoiceWithStatus(InvoiceStatus status) {
        List<Invoice> invoiceList = new ArrayList<>();
        for (Invoice invoice : invoices) {
            if (invoice.getInvoiceStatus() == status) {
                invoiceList.add(invoice);
            }
        }
        return invoiceList.size();
    }

    public List<Invoice> getFilteredInvoices(InvoiceStatus status) {
        List<Invoice> filterInvoices = new ArrayList<>();
        for (Invoice invoice : invoices) {
            if (invoice.getInvoiceStatus() == status) {
                filterInvoices.add(invoice);
            }
        }
        return filterInvoices;
    }

    public boolean getInvoiceListExists() {
        return invoiceList != null ? true : false;
    }

    void onActivate() {
        dashboardDao.updateInvoices();
        clients = clientDao.getAllClients();
        invoices = dashboardDao.getAllInvoices();
        sortedInvoices = new ArrayList<>(invoices);
        Collections.sort(sortedInvoices);

        invoiceGridModel = beanModelSource.createDisplayModel(Invoice.class, messages);
        invoiceGridModel.get("invoiceNumber").sortable(false);
        invoiceGridModel.get("invoiceDescription").sortable(false);
        invoiceGridModel.get("invoiceCurrency").sortable(false);
        invoiceGridModel.exclude("invoiceId");

//        LABELS
        invoiceGridModel.get("invoiceNumber").label("Invoice #");
        invoiceGridModel.get("invoiceDescription").label("Description");
        invoiceGridModel.get("invoiceIssueDate").label("Issue date");
        invoiceGridModel.get("invoiceDueDate").label("Due date");
        invoiceGridModel.get("invoiceStatus").label("Status");
        invoiceGridModel.get("invoiceCreationDate").label("Created on");
        invoiceGridModel.get("invoiceAmount").label("Amount");
        invoiceGridModel.get("invoiceCurrency").label("Currency");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.pages;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
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
public class Invoices {

    @Inject
    private DashboardDAO dashboardDao;
    
    @Inject
    private ClientDAO clientDao;

    @Property
    private Set<Invoice> invoices;
    
    @Property
    private List<Client> clients;

    @Property
    private Invoice invoice;

    @Property
    @Validate("required")
    private InvoiceCurrency invoiceCurrency;

    @Property
    private BeanModel<Invoice> invoiceGridModel;

    @Inject
    private Messages messages;
    @Inject
    private BeanModelSource beanModelSource;

    @Property
    private List<Invoice> sortedInvoices;

    public InvoiceCurrency[] getCurrencies() {
        return InvoiceCurrency.values();
    }

    public DecimalFormat getNumberFormat(){
        return new DecimalFormat("0");
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

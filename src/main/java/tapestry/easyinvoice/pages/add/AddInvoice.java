/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.pages.add;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import tapestry.easyinvoice.data.ClientDAO;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.entities.Invoice;
import tapestry.easyinvoice.model.InvoiceCurrency;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class AddInvoice {

    //TODO: Continue CRUD on adding invoices
    //TODO: Logic for adding services 
    // Client service and properties
    @Inject
    private ClientDAO clientDao;

    @Property
    @Validate("required")
    private Client client;

    @Property
    private List<Client> clients;

    //Invoice fields:
    @Property
    @Validate("required")
    private InvoiceCurrency currency;

    @Validate("required")
    @Property
    private String dueDate;

    @Property
    private Invoice invoice;

    @Validate("required")
    @Property
    private Date issueDate;

    @Validate("required")
    @Property
    private String invoiceNumber;
    

    public InvoiceCurrency[] getCurrencies() {
        return InvoiceCurrency.values();
    }

    public List<String> getDueDates() {
        List<String> dueDates = new ArrayList<>(Arrays.asList("Now", "10 days", "30 days", "60 days"));
        return dueDates;
    }

    public String getInvoiceNumberFormat(Date date) {
        SimpleDateFormat invoiceNumFormat = new SimpleDateFormat("YYYY-MMd");
        return invoiceNumFormat.format(date);
    }

    //TODO: Create refresh event to display invoice number
    
    void onPrepare() {
        if (clients == null) {
            clients = new ArrayList<>();
        }
        issueDate = new Date();
        invoiceNumber = getInvoiceNumberFormat(issueDate);
    }

    void onActivate() {
        clients = clientDao.getAllClients();
    }
}

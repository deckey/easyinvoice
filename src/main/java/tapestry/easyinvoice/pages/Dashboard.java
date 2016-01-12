/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.pages;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.hibernate.HibernateGridDataSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import tapestry.easyinvoice.data.ClientDAO;
import tapestry.easyinvoice.data.DashboardDAO;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.entities.Invoice;
import tapestry.easyinvoice.entities.Registration;
import tapestry.easyinvoice.entities.Service;
import tapestry.easyinvoice.model.InvoiceCurrency;
import tapestry.easyinvoice.model.InvoiceStatus;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class Dashboard {

    @Property
    private Client client;

    @Inject
    private ClientDAO clientDao;

    @Property
    private List<Client> clients;
    
    @InjectPage
    private Clients clientsPage;

    @Inject
    private DashboardDAO dashboardDao;

    @Property
    private Invoice invoice;
    @Property
    private Set<Invoice> invoices;

    @Property
    private Registration registration;

    @Property
    private Service service;

    @Property
    private Set<Service> services;

    @Inject
    private Session dbs;

    public GridDataSource getRegistrations() {
        return new HibernateGridDataSource(dbs, Registration.class);
    }

    public String getInvoiceAmount() {
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return formatter.format(invoice.getInvoiceAmount()) + " " + invoice.getInvoiceCurrency();
    }

     public List<Invoice> getInvoiceWithStatus(InvoiceStatus status) {
        List<Invoice> invoiceList = new ArrayList<>();
        for (Invoice invoice : invoices) {
            if (invoice.getInvoiceStatus() == status) {
                invoiceList.add(invoice);
            }
        }
        return invoiceList;
    }

    public boolean getCheckOverdue() {
        if (getInvoiceWithStatus(InvoiceStatus.Overdue).isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean getCheckClosed() {
        if (getInvoiceWithStatus(InvoiceStatus.Closed).isEmpty()) {
            return true;
        }
        return false;
    }

    public List<Invoice> getLatestInvoices() {
        List<Invoice> latestInvoices = new ArrayList<>();
        for (Invoice invoice : invoices) {
            if (invoice.getInvoiceStatus() == InvoiceStatus.Open) {
                latestInvoices.add(invoice);
            }
        }
        Collections.sort(latestInvoices, new Comparator<Invoice>() {
            public int compare(Invoice invoice1, Invoice invoice2) {
                return invoice1.getInvoiceCreationDate().after(invoice2.getInvoiceCreationDate()) ? 1 : -1;
            }
        });
        Collections.reverse(latestInvoices);
        if (latestInvoices.size() > 5) {
            latestInvoices = latestInvoices.subList(0, 5);
        }
        return latestInvoices;
    }

    public Client getLatestClient() {
        List<Client> latestClients = clientDao.getAllClients();

        Collections.sort(latestClients, new Comparator<Client>() {
            public int compare(Client client1, Client client2) {
                return (client1.getClientCreationDate().after(client2.getClientCreationDate())) ? -1 : 1;
            }
        });
        return latestClients.get(0);
    }

    public String getTotalIncome() {
        double income = 0;
        for (Client client : clients) {
            for (Invoice invoice : client.getInvoices()) {
                income += invoice.getInvoiceAmount();
            }
        }
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return formatter.format(income);
    }

    public String getClosedIncomeUSD() {
        return getClosedIncome("USD");
    }

    public String getClosedIncomeEUR() {
        return getClosedIncome("EUR");
    }

    public String getClosedIncomeGBP() {
        return getClosedIncome("GBP");
    }

    public String getIncome(String currency) {
        InvoiceCurrency curr = InvoiceCurrency.EUR;
        if (currency == "USD") {
            curr = InvoiceCurrency.USD;
        } else if (currency == "GBP") {
            curr = InvoiceCurrency.GBP;
        } else {
            curr = InvoiceCurrency.EUR;
        }

        double amount = 0.0;
        for (Client client : clients) {
            for (Invoice invoice : client.getInvoices()) {
                if (invoice.getInvoiceCurrency() == curr) {
                    amount += invoice.getInvoiceAmount();
                }
            }
        }
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        if (amount == 0) {
            return "0.00";
        }
        return formatter.format(amount);
    }

    public String getClosedIncome(String currency) {
        InvoiceCurrency curr = InvoiceCurrency.EUR;
        if (currency == "USD") {
            curr = InvoiceCurrency.USD;
        } else if (currency == "GBP") {
            curr = InvoiceCurrency.GBP;
        } else {
            curr = InvoiceCurrency.EUR;
        }

        double amount = 0.0;
        for (Client client : clients) {
            for (Invoice invoice : client.getInvoices()) {
                if (invoice.getInvoiceCurrency() == curr) {
                    if (invoice.getInvoiceStatus() == InvoiceStatus.Closed) {
                        amount += invoice.getInvoiceAmount();
                    }
                }
            }
        }
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        if (amount == 0) {
            return "0.00";
        }
        return formatter.format(amount);
    }
    
//    PAGE ACTIVATION CONTEXT
    void onActivate() {
        if (clients == null) {
            clients = new ArrayList<>();
        }
        if (services == null) {
            services = new HashSet<>();
        }
        if (invoices == null) {
            invoices = new HashSet<>();
        }
        clients = clientDao.getAllClients();
        invoices = dashboardDao.getAllInvoices();
        dashboardDao.updateInvoices();
    }
}

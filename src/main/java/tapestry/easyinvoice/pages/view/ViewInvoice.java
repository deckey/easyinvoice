/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.pages.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.easyinvoice.data.ClientDAO;
import tapestry.easyinvoice.data.DashboardDAO;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.entities.Company;
import tapestry.easyinvoice.entities.Invoice;
import tapestry.easyinvoice.entities.Registration;
import tapestry.easyinvoice.entities.Service;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class ViewInvoice {

    @Property
    private Company company;

    @Property
    private Client client;

    @Inject
    private ClientDAO clientDao;

    @Inject
    private DashboardDAO dashboardDao;

    @Property
    private Registration registration;

    @Inject
    private ComponentResources resources;

    @Property
    private Service service;

    @Property
    private List<Service> services;

    @Property
    @Persist
    private Invoice invoice;

    public String getFormattedDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.YYYY");
        return dateFormat.format(date);
    }

    public void set(Invoice invoice) {
        this.invoice = invoice;
    }

    void onActivate(Invoice invoice) {
        this.invoice = invoice;
        this.client = invoice.getClient();
        this.registration = client.getRegistration();
        this.services = new ArrayList<>(invoice.getServices());
        Collections.sort(services);

        resources.discardPersistentFieldChanges();
        company = clientDao.getAllCompanies().get(0);

    }

    Invoice onPassivate() {
        return invoice;
    }
}

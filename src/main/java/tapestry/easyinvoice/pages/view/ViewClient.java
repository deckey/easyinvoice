/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.pages.view;

import java.util.Set;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.easyinvoice.data.DashboardDAO;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.entities.Invoice;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class ViewClient {

    @Inject
    private DashboardDAO dashboardDao;

    @Inject
    private ComponentResources resources;

    @Property
    @Persist
    private Client client;
    
    @Property
    private Invoice invoice;
    
    @Property
    private Set<Invoice> invoices;

    public void set(Client client) {
        this.client = client;
    }

    void onActivate(Client client) {
        this.client = client;
        resources.discardPersistentFieldChanges();
        invoices = client.getInvoices();
    }

    Client onPassivate() {
        return client;
    }
}

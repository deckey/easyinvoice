/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.pages.view;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.easyinvoice.data.DashboardDAO;
import tapestry.easyinvoice.entities.Invoice;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class ViewInvoice {
    
    
    @Inject
    private DashboardDAO dashboardDao;
    
    @Inject
    private ComponentResources resources;
    
    
    @Property
    @Persist
    private Invoice invoice;
    
    
    
    public void set(Invoice invoice){
        this.invoice = invoice;
    }
    
    void onActivate(Invoice invoice){
        this.invoice = invoice;
        resources.discardPersistentFieldChanges();
    }
    
    Invoice onPassivate(){
        return invoice;
    }
}

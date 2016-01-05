/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.pages;

import java.util.Set;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.easyinvoice.data.DashboardDAO;
import tapestry.easyinvoice.entities.Invoice;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class Invoices {
    
    @Inject 
    private DashboardDAO dashboardDao;
    
    @Property
    private Set<Invoice> invoices;
    
    @Property
    private Invoice invoice;
    
    void setupRender(){
        invoices = dashboardDao.getAllInvoices();
        dashboardDao.updateInvoices();
    }
}

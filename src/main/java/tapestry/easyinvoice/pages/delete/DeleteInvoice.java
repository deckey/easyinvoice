/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.pages.delete;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.easyinvoice.data.DashboardDAO;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.entities.Invoice;
import tapestry.easyinvoice.pages.Invoices;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class DeleteInvoice {
    
    @Inject
    private DashboardDAO dashboardDao;
    
    @Property
    @Persist
    private Invoice invoice;
    
    public void set(Invoice invoice){
        this.invoice = invoice;
    }
    
    void onActivate(Invoice invoice){
        this.invoice = invoice;
    }
    
    Invoice onPassivate(){
        return invoice;
    }
    @CommitAfter
    Object onDeleteInvoice(Integer id){
        dashboardDao.deleteInvoice(id);
        return Invoices.class;
    }
}

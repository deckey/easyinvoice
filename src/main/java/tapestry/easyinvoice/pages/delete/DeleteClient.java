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
import tapestry.easyinvoice.data.ClientDAO;
import tapestry.easyinvoice.data.DashboardDAO;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.pages.Clients;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class DeleteClient {
    
    @Inject
    private ClientDAO clientDao;
    
    @Property
    @Persist
    private Client client;
    
    public void set(Client client){
        this.client = client;
    }
    
    void onActivate(Client client){
        this.client = client;
    }
    
    Client onPassivate(){
        return client;
    }
    @CommitAfter
    Object onDeleteClient(Integer id){
        clientDao.deleteClient(id);
        return Clients.class;
    }
}

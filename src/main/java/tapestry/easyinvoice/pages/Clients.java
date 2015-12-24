/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.pages;

import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.easyinvoice.data.ClientDAO;
import tapestry.easyinvoice.entities.Client;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class Clients {

    @Inject
    private ClientDAO clientDao;

    @Property
    private List<Client> clients;

    @Property
    private Client client;

    void onActivate() {
        clients = clientDao.getAllClients();
    }

}

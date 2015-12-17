/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.data;

import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import tapestry.easyinvoice.entities.Client;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class ClientIMPL implements ClientDAO {

    @Inject
    private Session dbs;

    @Override
    public void addClient(Client client) {
        dbs.persist(client);
    }

    @Override
    public List<Client> getAllClients() {
        return dbs.createCriteria(Client.class).list();
    }

    @Override
    public Client findClientById(Integer id) {
        return (Client) dbs.createCriteria(Client.class).add(Restrictions.eq("clientId", id)).uniqueResult();
    }

    @Override
    public Client updateClient(Client client) {
        return (Client) dbs.merge(client);
    }
}

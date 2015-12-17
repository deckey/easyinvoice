/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.data;

import java.util.List;
import tapestry.easyinvoice.entities.Client;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public interface ClientDAO {
    
    public void addClient(Client client);
    
    public List<Client> getAllClients();
    
    public Client findClientById(Integer id);
    
    public Client updateClient(Client client);
}

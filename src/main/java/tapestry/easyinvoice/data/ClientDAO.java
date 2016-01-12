/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.data;

import java.util.List;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.entities.Company;
import tapestry.easyinvoice.entities.Service;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public interface ClientDAO {
    
    public void addClient(Client client);
    
    public void deleteClient(Integer id);
    
    public List<Client> getAllClients();
    public List<Service> getAllServices();
    public double getClientTotalAmount(Client client);
    
    public Client findClientById(Integer id);
    
    public List<Company> getAllCompanies();
    public Company findCompanyByName(String companyName);
    
    public Client findClientByCompanyName(String companyName);
    
    public Client updateClient(Client client);
}

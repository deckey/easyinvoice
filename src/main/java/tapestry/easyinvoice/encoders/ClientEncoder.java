
package tapestry.easyinvoice.encoders;

import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.easyinvoice.data.ClientDAO;
import tapestry.easyinvoice.entities.Client;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class ClientEncoder implements ValueEncoder<Client>{

    @Inject
    private ClientDAO clientDao;

    /**
     * Constructor
     * @param memberDao default constructor parameter
     */
    public ClientEncoder(ClientDAO clientDao) {
        this.clientDao = clientDao;
    }
    
    /**
     * Override of toClient() method
     * @param client Client instance
     * @return String representation of the client
     */
    @Override
    public String toClient(Client client) {
        // return the given object's username
        return String.valueOf(client.getClientId());
    }

    /**
     * Override of toValue() method
     * @param id Member unique id as string
     * @return Member instance found in DB
     */
    @Override
    public Client toValue(String id) {
        // find the member object of the given ID in the database
        return clientDao.findClientById(Integer.parseInt(id));
    }
}

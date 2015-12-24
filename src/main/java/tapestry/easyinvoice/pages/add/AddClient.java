/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.pages.add;

import org.apache.tapestry5.annotations.Property;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.entities.Registration;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class AddClient {

    @Property
    private Client client;

    @Property
    private Registration registration;

    @Property
    private String clientCompany;
    @Property
    private String clientContact;
    @Property
    private String clientEmail;
    @Property
    private String clientPhone;
    @Property
    private String clientWebsite;

    @Property
    private String registrationAddress;
    @Property
    private String registrationCity;
    @Property
    private String registrationCountry;
    
    @Property
    private String registrationShipAddress;
    @Property
    private String registrationShipCity;
    @Property
    private String registrationShipCountry;

}

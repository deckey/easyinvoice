/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import tapestry.easyinvoice.data.ClientDAO;
import tapestry.easyinvoice.entities.Client;
import tapestry.easyinvoice.entities.Invoice;
import tapestry.easyinvoice.entities.Registration;
import tapestry.easyinvoice.entities.Service;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class Clients {

    @Inject
    private AjaxResponseRenderer response;

    @Inject
    private ClientDAO clientDao;

    @Property
    private List<Client> clients;

    @Property
    private Client client;

    @Property
    private Invoice invoice;

    @Property
    private List<Invoice> invoices;

    @Property
    private BeanModel<Client> clientGridModel;

    @Inject
    private Messages messages;

    @Inject
    private BeanModelSource beanModelSource;

    @Property
    private Registration registration;

    @Inject
    private Request request;

    @Property
    private Service service;

    @InjectComponent
    private Zone registrationZone;

//    PAGE ACTIVATION CONTEXT
    void onActivate() {
        clients = clientDao.getAllClients();
        registration = null;
        invoices = new ArrayList<>();
    }
    public boolean getCheckRegistration() {
        return registration != null ? true : false;
    }

    void setupRender() {

        clientGridModel = beanModelSource.createDisplayModel(Client.class, messages);
        clientGridModel.get("clientCompany").label("Company");
        clientGridModel.get("clientCompany").sortable(false);
        clientGridModel.get("clientTaxId").sortable(false);
        clientGridModel.get("clientPhone").sortable(false);
        clientGridModel.get("clientEmail").sortable(false);
        clientGridModel.get("clientWebsite").sortable(false);
    }

    public void onSelectClient(Integer id) {
        client = clientDao.findClientById(id);
        invoices = new ArrayList<>(client.getInvoices());
        Collections.sort(invoices);
        registration = client.getRegistration();
        response.addRender(registrationZone);
    }
}

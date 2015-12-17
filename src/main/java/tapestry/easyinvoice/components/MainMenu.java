/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class MainMenu {

    @Inject
    private ComponentResources componentResource;

    @Property
    private String pageName;

    public List<String> getPageNames() {
        List<String> pageNames = new ArrayList<>(Arrays.asList("Dashboard", "Invoices", "Clients"));
        return pageNames;
    }

    public String getClassForPage() {
        return componentResource.getPageName().equalsIgnoreCase(pageName) ? "active" : null;
    }

    public String getPageLabel() {
        List<String> pageNames = getPageNames();
        String[] pageLabels = {"Dashboard", "Invoices","Clients"};
        return pageLabels[pageNames.indexOf(pageName)];
    }
    
}

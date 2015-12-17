/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.easyinvoice.entities.Member;
import tapestry.easyinvoice.pages.AdminArea;
import tapestry.easyinvoice.pages.Index;
import tapestry.easyinvoice.model.MemberRole;
import tapestry.easyinvoice.pages.Dashboard;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class Header {

    @Inject
    private ComponentResources resources;

    @SessionState
    private Member loggedInUser;

    // return name of the logged user
    public String getLoggedUser() {
        return loggedInUser.getMemberName();
    }

    //Event from logout button, clears user from the session
    Object onLogout() {
        resources.discardPersistentFieldChanges();
        return Index.class;
    }

    //Check if user is Admin and can access admin area
    Object onEnterAdminArea() {
        return loggedInUser.getMemberRole().equals(MemberRole.Administrator) ? AdminArea.class : Dashboard.class;
    }
}

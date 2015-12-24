package tapestry.easyinvoice.pages;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.HibernatePersistenceConstants;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.easyinvoice.data.DashboardDAO;
import tapestry.easyinvoice.entities.Member;

/**
 * Start page of application EasyInvoice.
 */
public class Index {
    
    @Inject
    private DashboardDAO dashboardDao;

    @SessionState
    private Member loggedInUser;
    
    @Property
    @Validate("required")
    private String memberPassword;

    @Property
    @Validate("required")
    private String memberUsername;

    @InjectComponent("loginForm")
    private Form form;

    Object onValidateFromLoginForm() {
        if(dashboardDao.validateMember(memberUsername, memberPassword)) {
            loggedInUser = dashboardDao.findMemberByUsername(memberUsername);
            return Dashboard.class;
        } else {
            form.recordError("Invalid username or password!");
            return null;
        }
    }
}

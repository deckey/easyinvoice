package tapestry.easyinvoice.pages;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Errors;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Start page of application EasyInvoice.
 */
public class Index {

    @Property
    @Validate("required")
    private String memberPassword;

    @Property
    @Validate("required")
    private String memberUsername;

    @InjectComponent("loginForm")
    private Form form;

    boolean validateUser(String uName, String pWord) {
        return (uName.equals("admin") && pWord.equals("admin"));
    }

    Object onValidateFromLoginForm() {
        if (validateUser(memberUsername, memberPassword)) {
            return Dashboard.class;
        } else {
            form.recordError("Invalid username or password!");
            return null;
        }
    }
}

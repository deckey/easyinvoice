package tapestry.easyinvoice.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * Dejan Ivanovic divanovic3d@gmail.com
 */
@Import(stylesheet = {
    "context:css/normalize.css",
    "context:css/bootstrap-custom.css",
    "context:css/main.css"}, library = "context:js/bootstrap.js")

public class Layout {

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String title;

}

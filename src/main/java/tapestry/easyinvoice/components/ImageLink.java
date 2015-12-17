//TODO: Continue mockup and DB entities
//TODO: Fix main menu icons and link positions - finish responsive class
package tapestry.easyinvoice.components;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class ImageLink {
    
    @Parameter(defaultPrefix = "literal")
    @Property
    private String linkText;
    
    @Parameter(defaultPrefix = "literal",required=true)
    @Property
    private String link;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.mixins;

import java.util.List;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.MixinAfter;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.beaneditor.PropertyModel;
import org.apache.tapestry5.corelib.components.Grid;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@MixinAfter
public class GridSortingDisabler {

    @InjectContainer
    private Grid grid;

    void setupRender() {
        if (grid.getDataSource().getAvailableRows() == 0) {
            return;
        }

        BeanModel<?> gridBeanModel = grid.getDataModel();
        List<String> propertyNames = gridBeanModel.getPropertyNames();

        for (String propertyName : propertyNames) {
            PropertyModel propertyModel = gridBeanModel.get(propertyName);
            propertyModel.sortable(false);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.model;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public enum InvoiceCurrency {

    USD("$"), EUR("€"), GBP("£");

    private String value;

    private InvoiceCurrency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}

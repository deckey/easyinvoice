
package tapestry.easyinvoice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Entity
@Table(name="company")
public class Company {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;
    
    private String companyName;
    private String companyAddress;
    private String companyZip;
    private String companyCity;
    private String companyCountry;
    private String companyPhone;
    private String companyCRNumber;
    private String companyTaxId;
    private String companyBusinessCode;
    // Company bank details
    private String companySwiftCode;
    private String companyBankName;
    private String companyBankAddress;
    private String companyIban;
    private String companyCorrespondent;
    
    @Inject
    public Company() {
    }

    public Company(String companyName, String companyAddress, String companyZip, String companyCity, String companyCountry, String companyPhone, String companyCRNumber, String companyTaxId, String companyBusinessCode, String companySwiftCode, String companyBankName, String companyBankAddress, String companyIban, String companyCorrespondent) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyZip = companyZip;
        this.companyCity = companyCity;
        this.companyCountry = companyCountry;
        this.companyPhone = companyPhone;
        this.companyCRNumber = companyCRNumber;
        this.companyTaxId = companyTaxId;
        this.companyBusinessCode = companyBusinessCode;
        this.companySwiftCode = companySwiftCode;
        this.companyBankName = companyBankName;
        this.companyBankAddress = companyBankAddress;
        this.companyIban = companyIban;
        this.companyCorrespondent = companyCorrespondent;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyZip() {
        return companyZip;
    }

    public void setCompanyZip(String companyZip) {
        this.companyZip = companyZip;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyCountry() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyCRNumber() {
        return companyCRNumber;
    }

    public void setCompanyCRNumber(String companyCRNumber) {
        this.companyCRNumber = companyCRNumber;
    }

    public String getCompanyTaxId() {
        return companyTaxId;
    }

    public void setCompanyTaxId(String companyTaxId) {
        this.companyTaxId = companyTaxId;
    }

    public String getCompanyBusinessCode() {
        return companyBusinessCode;
    }

    public void setCompanyBusinessCode(String companyBusinessCode) {
        this.companyBusinessCode = companyBusinessCode;
    }

    public String getCompanySwiftCode() {
        return companySwiftCode;
    }

    public void setCompanySwiftCode(String companySwiftCode) {
        this.companySwiftCode = companySwiftCode;
    }

    public String getCompanyBankName() {
        return companyBankName;
    }

    public void setCompanyBankName(String companyBankName) {
        this.companyBankName = companyBankName;
    }

    public String getCompanyBankAddress() {
        return companyBankAddress;
    }

    public void setCompanyBankAddress(String companyBankAddress) {
        this.companyBankAddress = companyBankAddress;
    }

    public String getCompanyIban() {
        return companyIban;
    }

    public void setCompanyIban(String companyIban) {
        this.companyIban = companyIban;
    }

    public String getCompanyCorrespondent() {
        return companyCorrespondent;
    }

    public void setCompanyCorrespondent(String companyCorrespondent) {
        this.companyCorrespondent = companyCorrespondent;
    }
    
}

package tapestry.easyinvoice.pages;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.easyinvoice.data.ClientDAO;
import tapestry.easyinvoice.data.DashboardDAO;
import tapestry.easyinvoice.entities.Company;
import tapestry.easyinvoice.entities.Member;
import tapestry.easyinvoice.services.ProtectedPage;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@ProtectedPage
@RolesAllowed(value = {"Administrator"})
public class AdminArea {

    @Inject
    private AlertManager alertManager;

    @Property
    private Company company;

    //Company fields
    @Property
    @Validate("required")
    private String companyName;
    @Property
    @Validate("required")
    private String companyAddress;
    @Property
    @Validate("required")
    private String companyZip;
    @Property
    @Validate("required")
    private String companyCity;
    @Property
    private String companyCountry;
    @Property
    @Validate("required")
    private String companyPhone;
    @Property
    @Validate("required")
    private String companyCRNumber;
    @Property
    @Validate("required")
    private String companyTaxId;
    @Property
    @Validate("required")
    private String companyBusinessCode;
    @Property
    @Validate("required")
    private String companyBankName;
    @Property
    @Validate("required")
    private String companyBankAddress;
    @Property
    @Validate("required")
    private String companyIban;
    @Property
    @Validate("required")
    private String companySwiftCode;
    @Property
    private String companyCorrespondent;

    @Inject
    private ClientDAO clientDao;
    @Inject
    private DashboardDAO dashboardDao;

    @InjectComponent("addCompanyForm")
    private Form addCompanyForm;

    @InjectComponent("addMemberForm")
    private BeanEditForm form;

    @Property
    @Persist
    private Member member;

    @Inject
    private Messages messages;

    @Property
    private Member rowMember;

    @Property
    private List<Member> members;

    public String getOutputPassword() {
        return rowMember.getMemberPassword().replaceAll(".", "*");
    }

    void onEditMember(Member aMember) {
        this.member = aMember;
    }

    @CommitAfter
    void onDeleteMember(Integer id) {
        dashboardDao.deleteMember(id);
    }

    @CommitAfter
    Object onSuccessFromAddMemberForm() {
        dashboardDao.updateMember(member);
        alertManager.alert(Duration.TRANSIENT, Severity.SUCCESS, "User " + member.getMemberName() + " successfully updated!");
        member = new Member();
        return this;
    }

//    COMPANY FORM
    void onValidateFromAddCompanyForm() {
        System.out.println("VALIDATING.........");

    }

    @CommitAfter
    void onSuccessFromAddCompanyForm() {
        company.setCompanyName(companyName);
        company.setCompanyAddress(companyAddress);
        company.setCompanyZip(companyZip);
        company.setCompanyCity(companyCity);
        company.setCompanyCountry(companyCountry);
        company.setCompanyPhone(companyPhone);

        company.setCompanyCRNumber(companyCRNumber);
        company.setCompanyTaxId(companyTaxId);
        company.setCompanyBusinessCode(companyBusinessCode);

        company.setCompanyBankName(companyBankName);
        company.setCompanyBankAddress(companyBankAddress);
        company.setCompanyIban(companyIban);
        company.setCompanySwiftCode(companySwiftCode);
        company.setCompanyCorrespondent(companyCorrespondent);

        System.out.println("ADD COMPANY FORM ..........SUCCESS");
    }

    void onPrepare() {
        if (members == null) {
            members = new ArrayList<>();
        }
    }

    void onActivate() {
        members = dashboardDao.getAllMembers();

//        POPULATE COMPANY FORM
        company = clientDao.getAllCompanies().get(0);
        if (company == null) {
            company = new Company();
        }
        companyName = company.getCompanyName();
        companyAddress = company.getCompanyAddress();
        companyZip = company.getCompanyZip();
        companyCity = company.getCompanyCity();
        companyCountry = company.getCompanyCountry();
        companyPhone = company.getCompanyPhone();
        companyCRNumber = company.getCompanyCRNumber();
        companyTaxId = company.getCompanyTaxId();
        companyBusinessCode = company.getCompanyBusinessCode();
        companyBankName = company.getCompanyBankName();
        companyBankAddress = company.getCompanyBankAddress();
        companyIban = company.getCompanyIban();
        companySwiftCode = company.getCompanySwiftCode();
        companyCorrespondent = company.getCompanyCorrespondent();
    }
}

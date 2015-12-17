package tapestry.easyinvoice.pages;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.easyinvoice.data.DashboardDAO;
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
    private DashboardDAO dashboardDao;

    @Property
    @Persist
    private Member member;

    @Property
    private Member rowMember;

    @Property
    private List<Member> members;

    void onEditMember(Member aMember) {
        System.out.println("MEMBER..." + member.getMemberName());
        this.member = aMember;
    }

    @CommitAfter
    void onDeleteMember(Integer id) {
        dashboardDao.deleteMember(id);
    }

    @CommitAfter
    Object onSuccessFromAddMemberForm() {
        dashboardDao.updateMember(member);
        member = new Member();
        return this;
    }

    void onPrepare() {
        if (members == null) {
            members = new ArrayList<>();
        }
    }

    void onActivate() {
        members = dashboardDao.getAllMembers();
    }

}

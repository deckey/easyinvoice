/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.data;

import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import tapestry.easyinvoice.entities.Member;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class DashboardIMPL implements DashboardDAO {

    @Inject
    private Session dbs;
    
    @Override
    public void deleteMember(Integer id) {
        Member member = (Member) dbs.createCriteria(Member.class).add(Restrictions.eq("memberId", id)).uniqueResult();
        dbs.delete(member);
    }
    
    @Override
    public Member findMemberByUsername(String uName) {
        return (Member) dbs.createCriteria(Member.class).add(Restrictions.eq("memberUsername", uName)).uniqueResult();
    }
    @Override
    public List<Member> getAllMembers() {
        return dbs.createCriteria(Member.class).list();
    }

    @Override
    public void updateMember(Member member) {
        dbs.merge(member);
    }

    @Override
    public boolean validateMember(String uName, String pWord) {
        Member member = (Member) dbs.createCriteria(Member.class)
                .add(Restrictions.eq("memberUsername", uName))
                .add(Restrictions.eq("memberPassword", pWord))
                .uniqueResult();
        return member != null;
    }

}

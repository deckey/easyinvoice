/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.data;

import java.util.List;
import tapestry.easyinvoice.entities.Member;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public interface DashboardDAO {

    public void deleteMember(Integer id);

    public Member findMemberByUsername(String uName);

    public List<Member> getAllMembers();

    public void updateMember(Member member);

    public boolean validateMember(String uName, String pWord);

}

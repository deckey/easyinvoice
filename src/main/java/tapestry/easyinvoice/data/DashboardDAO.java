/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.data;

import java.util.List;
import java.util.Set;
import tapestry.easyinvoice.entities.Invoice;
import tapestry.easyinvoice.entities.Member;
import tapestry.easyinvoice.entities.Service;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public interface DashboardDAO {

    public void addInvoice(Invoice invoice);

    public void addService(Service service);

    public boolean checkIfInvoiceExists(String clientCompany, String aNumber);

    public void deleteInvoice(Integer id);
    
    public void deleteMember(Integer id);

    public Member findMemberById(Integer id);
    
    public Member findMemberByUsername(String uName);

    public Set<Invoice> getAllInvoices();
    
    public List<Member> getAllMembers();
    
    public void updateInvoices();

    public void updateMember(Member member);

    public boolean validateMember(String uName, String pWord);

}

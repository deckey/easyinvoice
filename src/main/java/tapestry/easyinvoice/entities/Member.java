/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;
import tapestry.easyinvoice.model.MemberRole;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Entity
@Table(name="member")
public class Member implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="memberId")
    private Integer memberId;
    
    @Validate("required")
    @Column(name="memberName")
    private String memberName;
    
    @Validate("required")
    @Column(name="memberUsername")
    private String memberUsername;
    
    @Validate("required")
    @Column(name="memberPassword")
    private String memberPassword;
    
    @Validate("required")
    @Column(name="memberRole")
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Inject
    public Member() {
    }

    public Member(String memberName, String memberUsername, String memberPassword) {
        this.memberName = memberName;
        this.memberUsername = memberUsername;
        this.memberPassword = memberPassword;
        this.memberRole = MemberRole.User;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public MemberRole getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(MemberRole memberRole) {
        this.memberRole = memberRole;
    }
    
}

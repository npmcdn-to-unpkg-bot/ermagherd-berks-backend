package me.totlol.ermagherdberks.service;

import lombok.val;
import me.totlol.ermagherdberks.controller.MemberController;
import me.totlol.ermagherdberks.entity.Member;
import me.totlol.ermagherdberks.entity.MemberType;
import me.totlol.ermagherdberks.repository.MemberRepository;
import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Márton Tóth
 */
@Service
public class MemberService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void postConstruct() {
        Member member = memberRepository.findByEmail("admin@admin.com");
        if (member == null) {
            log.info("Couldn't find admin@admin.com in the db. Inserting it.");
            member = new Member();
            member.setEmail("admin@admin.com");
            // admin
            member.setPassword("$2a$04$Z9DuCzLDntNqmqxnupfgvufZz26AfU7dyiBd/dSDzAayCDwrTk6Hq");
            member.setMemberType(MemberType.ADMIN);
            memberRepository.save(member);
        }
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public Member saveMember(Member member) {
        if (member.getPassword() != null) {
            member.setPassword(passwordEncoder.encode(member.getPassword()));
        } else {
            val memberToBeUpdated = memberRepository.findOne(member.getId());
            member.setPassword(memberToBeUpdated.getPassword());
        }
        return memberRepository.save(member);
    }

    public void deleteMember(Long memberId) {
        memberRepository.delete(memberId);
    }

    public Member findMember(Long id) {
        return memberRepository.findOne(id);
    }
}

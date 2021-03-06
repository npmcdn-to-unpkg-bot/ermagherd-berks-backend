package me.totlol.ermagherdberks.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.totlol.ermagherdberks.entity.Member;
import me.totlol.ermagherdberks.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Márton Tóth
 */
@RestController
@Api("Members")
@RequestMapping("api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("getAllMembers")
    @Secured("ROLE_ADMIN")
    public List<Member> getMembers() {
        return memberService.findAllMembers()
                .stream()
                .peek(m -> m.setPassword(null))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ApiOperation("getMember")
    @Secured("ROLE_ADMIN")
    public Member getMember(@PathVariable("id") Long id) {
        return memberService.findMember(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("createMember")
    @Secured("ROLE_ADMIN")
    public Member createMember(@RequestBody Member member) {
        return memberService.saveMember(member);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation("updateMember")
    @Secured("ROLE_ADMIN")
    public Member updateMember(@RequestBody Member member) {
        return member;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation("deleteMember")
    @Secured("ROLE_ADMIN")
    public void deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
    }

    @RequestMapping(value = "me", method = RequestMethod.GET)
    @ApiOperation("me")
    @Secured("ROLE_MEMBER")
    public Member me(@AuthenticationPrincipal Member member) {
        member.setPassword(null);
        return member;
    }

}

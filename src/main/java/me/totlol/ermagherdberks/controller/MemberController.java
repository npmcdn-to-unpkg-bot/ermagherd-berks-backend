package me.totlol.ermagherdberks.controller;

import io.swagger.annotations.Api;
import me.totlol.ermagherdberks.entity.Member;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * Created by Márton Tóth
 */
@RestController
@Api("Members")
@RequestMapping("api/members")
public class MemberController {

    @RequestMapping(method = RequestMethod.GET)
    public List<Member> getMembers() {
        return Collections.emptyList();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Member getMember(@PathVariable("id") Long id) {
        return new Member();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Member createMember(@RequestBody Member member) {
        return member;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Member updateMember(@RequestBody Member member) {
        return member;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteMember(@PathVariable("id") Long id) {

    }
}

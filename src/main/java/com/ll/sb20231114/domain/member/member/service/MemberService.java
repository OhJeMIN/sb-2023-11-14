package com.ll.sb20231114.domain.member.member.service;

import com.ll.sb20231114.domain.member.member.Repository.MemberRepository;
import com.ll.sb20231114.domain.member.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //저는 단 한번만 생성되고, 그 이우에는 재사용되는 객체입니다.
@RequiredArgsConstructor
public class MemberService {
    /*@Autowired 필드 주입 --> 객체만들고 나중에 받는것
    private final MemberRepository memberRepository;  final 불가능 왜냐면 만들면 이미 null 이 들어가 바꿀 수 없기 때문이다. */
    private final MemberRepository memberRepository;// 생성자로 주입할땐 final 가능 , 만들자마자 바로 예시로(칼) 이 들어가기 떄문에 된다.
    /*@Autowired // 생성자 주입 --> 객체만들면서 바로 주기, 만약 생성자가 하나라면 AUtoWired 생략 가능
    public MemberService(MemberRepository memberRepository){
        this.memberRepository =memberRepository;
    }*/

    public Member join(String username, String password) {
        Member member = new Member(username, password);
        memberRepository.save(member);
        return member;
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Optional<Member> findById(long id) {
        return memberRepository.findById(id);
    }

    public void delete(long id) {
        memberRepository.delete(id);
    }

    public void modify(long id, String password, String username) {
        Member artlce = findById(id).get();
        artlce.setUsername(username);
        artlce.setPassword(password);
    }
}

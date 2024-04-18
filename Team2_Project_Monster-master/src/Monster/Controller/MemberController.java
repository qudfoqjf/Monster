package Monster.Controller;

import Monster.Model.Dao.MemberDao;
import Monster.Model.Dto.MemberDto;
import Monster.View.MainView;

public class MemberController {
    //싱글톤
    private MemberController(){}
    private static MemberController memberController = new MemberController();
    public static MemberController getInstance(){return memberController;}

    //메소드
    //회원가입
    public int signup(MemberDto memberDto){
        int result = 0;

        if(MemberDao.getInstance().idCheck(memberDto.getMid())){
            return 1;
        }
        result = MemberDao.getInstance().signup(memberDto);
        return result;
    }

    //로그인상태
    public int logMno=0;

    //로그인
    public int login(MemberDto memberDto){
        int result = 0;

        int result1 = MemberDao.getInstance().loginId(memberDto);
        int result2 = MemberDao.getInstance().loginPw(memberDto);
        //System.out.println(result2);


        if(result1==1&&result2==1){
            //로그인상태확인 회원번호
            //System.out.println(logMno);
            result = 1; //로그인 성공
            logMno = MemberDao.getInstance().findMno(memberDto.getMid());
        }else if (result1 == 2){
            result = 2; //아이디틀림
        } else if (result2==2) {
            result = 3; //비밀번호 틀림
        }
        return result;
    }

    //로그아웃
    public void logOut(){
        logMno = 0;
    }

    //비밀번호확인
    public boolean checkPw(MemberDto memberDto){

        //다오에게 값받기
        memberDto.setMno(logMno);
        boolean result = MemberDao.getInstance().checkPw(memberDto);
        return result;
    }

    //비밀번호변경
    public boolean rePw(MemberDto memberDto1){
        boolean result = false;
        memberDto1.setMno(logMno);
        result = MemberDao.getInstance().rePw(memberDto1);
        return result;
    }

    //회원탈퇴
    public boolean withdraw(MemberDto memberDto){

        //다오에게 값받기
        memberDto.setMno(logMno);
        boolean result = MemberDao.getInstance().withdraw(memberDto);

        return result;
    }

}

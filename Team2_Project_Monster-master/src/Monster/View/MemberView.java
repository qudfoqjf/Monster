package Monster.View;

import Monster.Controller.MemberController;
import Monster.Model.Dto.MemberDto;

import java.util.Scanner;

public class MemberView {
    //싱글톤
    private MemberView(){}
    private static MemberView memberView = new MemberView();
    public static MemberView getInstance(){return memberView;}

    //입력객체
    Scanner scanner = MainView.getInstance().scanner;

    //1.회원가입
    public void signup(){
        //입력받기
        System.out.println("==============================회원가입==============================");
        System.out.print("아이디 : ");
        String id = scanner.next();
        System.out.print("비밀번호 : ");
        String pw = scanner.next();
        System.out.print("핸드폰 : ");
        String phone = scanner.next();
        System.out.print("이름 : ");
        String name = scanner.next();

        //객체화
        MemberDto memberDto = new MemberDto(0,id,pw,phone,name);

        //컨트롤러에 전달 후 받기
        int result = MemberController.getInstance().signup(memberDto);

        if(result==0){
            System.out.println("<알림> 회원가입 되었습니다.");
            MainView.getInstance().mainView();
        } else if (result == 1) {
            System.out.println("<알림> 아이디가 중복되었습니다.");
        }

    }//m e

    //로그인
    public void login(){
        System.out.println("==============================로그인==============================");

        //입력받기
        System.out.print("아이디 : ");
        String id = scanner.next();
        System.out.print("비밀번호 : ");
        String pw = scanner.next();

        if(id.equals("admin")){
            if (pw.equals("admin")){
                AdminView.AdminRun();
            }
        }

        //객체화
        MemberDto memberDto = new MemberDto();
        memberDto.setMid(id);
        memberDto.setMpw(pw);

        //컨트롤에게 전달 후 받기
        int result = MemberController.getInstance().login(memberDto);

        boolean login =true;
        while ( login ){
            if(result == 1){
                System.out.println("============================몬스터키우기===========================");
                System.out.println("1.플레이 2.설정");
                int ch = scanner.nextInt();
                if (ch == 1){
                    MonsterPlayView.getInstance().run();
                } else if (ch==2) {
                    login = SettingView.getInstance().set();
                } else {
                    System.out.println("<알림> 입력이 잘못되었습니다.");
                    login = false;
                }
            }else if(result == 2){
                System.out.println("<알림> 없는 아이디 입니다.");
                login = false;
            } else if (result==3) {
                System.out.println("<알림> 잘못된 비밀번호 입니다.");
                login = false;
            }else {
                System.out.println("<시스템오류> 관리자에게 문의하세요");
                login = false;
            }


        }



    }






}//c e

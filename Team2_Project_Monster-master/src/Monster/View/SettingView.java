package Monster.View;

import Monster.Controller.MemberController;
import Monster.Model.Dto.MemberDto;

import java.util.Scanner;

public class SettingView {
    //싱글톤
    private SettingView(){}
    private static SettingView settingView = new SettingView();
    public static SettingView getInstance(){return settingView;}

    //입력객체
    Scanner scanner = MemberView.getInstance().scanner;

    //설정
    public boolean set(){


            System.out.println("===============================설정===============================");
            System.out.println("1.비밀번호변경 2.회원탈퇴 3.로그아웃[main] 4.뒤로가기[플레이/설정]");
            int ch2 = scanner.nextInt();

            if (ch2==1){//비밀번호 변경
                System.out.println("============================비밀번호변경============================");
                System.out.print("비밀번호 : ");
                String pw = scanner.next();

                //객체화
                MemberDto memberDto = new MemberDto(pw);

                //컨트롤에게 보내주고 값받기
                boolean result = MemberController.getInstance().checkPw(memberDto);

                if(result){
                    System.out.println("============================비밀번호변경============================");
                    System.out.print("변경할 비밀번호 : ");
                    String rePw = scanner.next();

                    MemberDto memberDto1 = new MemberDto(rePw);

                    MemberController.getInstance().rePw(memberDto1);

                }else {
                    System.out.println("<알림> 잘못된 비밀번호 입니다.");
                    set();

                }


            } else if (ch2==2) {//회원탈퇴
                System.out.println("==============================회원탈퇴=============================");
                System.out.print("비밀번호 : ");
                String pw = scanner.next();

                //객체화
                MemberDto memberDto = new MemberDto();
                memberDto.setMpw(pw);

                //컨트롤에게 보내주고 값받기

                boolean check = MemberController.getInstance().checkPw(memberDto);
                if (check){
                    boolean result = MemberController.getInstance().withdraw(memberDto);
                    if (result){
                        System.out.println("<알림> 탈퇴가 완료되었습니다.");
                        return false;
                    }else {
                        System.out.println("<알림> 시스템오류 관리자에게 문의하세요");
                        set();
                    }
                }else {
                    System.out.println("<알림> 잘못된 비밀번호 입니다.");
                    set();
                }

            } else if (ch2==3) {//로그아웃[main]
                MemberController.getInstance().logOut();
                return false;
            } else if (ch2==4) {//뒤로가기[플레이/설정]
                return true;
            }
            return false;
        }
}

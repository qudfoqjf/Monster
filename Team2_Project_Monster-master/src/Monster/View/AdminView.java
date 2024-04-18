package Monster.View;

import Monster.Controller.AdminController;
import Monster.Controller.MemberController;
import Monster.Model.Dto.EventDto;
import Monster.Model.Dto.MemberDto;
import Monster.Model.Dto.MonsterDto;
import Monster.Model.Dto.MonsterListDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminView {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //싱글톤
    private AdminView(){}
    private static AdminView adminView= new AdminView();

    public static  AdminView getInstance(){return adminView;}

    static Scanner scanner =new Scanner(System.in);


    // 0. 관리자 로그인 성공시 출력

    public static void AdminRun(){
        while (true){
            System.out.println("============================관리자 모드============================");
            System.out.println("1. 회원관리 2. 육성관리 3. 로그아웃");
            System.out.println("선택>"); int ch= scanner.nextInt();

            if(ch==1){
                System.out.println("회원관리메소드");
                MemberManagement(); //회원관리 메소드
            }
            else if (ch==2) {
                System.out.println("육성관리메소드");
                FosterManagement();
            }
            else if (ch==3) {
                System.out.println("로그아웃 로그인화면으로이동");
                MemberController.getInstance().logOut();
                return;
            }

        }


    }
    //회원관리 화면
    public static void MemberManagement(){
        // 회원관리====================================================
        while (true){
            System.out.println("============================회원 관리============================");
            ArrayList<MemberDto> memberDtos = AdminController.getInstance().memberPrint(new MemberDto());
            for (int i=0;i<memberDtos.size();i++){
                System.out.print(memberDtos.get(i).getMno()+"\t");
                System.out.print(memberDtos.get(i).getMid()+"\t");
                System.out.print(memberDtos.get(i).getMpw()+"\t");
                System.out.print(memberDtos.get(i).getMphone()+"\t");
                System.out.println(memberDtos.get(i).getMname());
            }
            System.out.println("회원번호입력:"); int mno=scanner.nextInt();

            MemberDto admem = AdminController.getInstance().memprin(mno);


            if(admem!=null) {

                System.out.print(admem.getMno()+"\t");
                System.out.print(admem.getMid()+"\t");
                System.out.print(admem.getMpw()+"\t");
                System.out.print(admem.getMphone()+"\t");
                System.out.println(admem.getMname());


                // 회원 정보 수정/삭제 선택
                System.out.println("============================회원 관리============================");
                System.out.println("1. 회원 정보 수정 2. 회원 정보 삭제 3.뒤로 가기");
                System.out.println("선택>");
                int ch = scanner.nextInt();
                // 정보 수정===============================================
                if (ch == 1) {

                    scanner.nextLine();
                    System.out.print("아이디:");
                    String id = scanner.nextLine();
                    System.out.print("비밀번호:");
                    String pw = scanner.nextLine();
                    System.out.print("전화번호:");
                    String phone = scanner.nextLine();
                    System.out.print("이름:");
                    String name = scanner.nextLine();
                    MemberDto memberDto = new MemberDto(mno, id, pw, phone, name);


                    boolean result = AdminController.getInstance().memberChange(memberDto);

                    return;
                }
                // 정보 삭제=====================================================
                else if (ch == 2) {
                    System.out.println("삭제");
                    int result = AdminController.getInstance().memberDelete(mno);
                    if (result == 1) {
                        System.out.println("회원번호" + mno + "번이 삭제되었습니다.");
                    } else {
                        System.out.println("삭제 실패");
                    }
                    return;
                }else if(ch==3){
                    System.out.println("뒤로 가기");
                    return;
                }
            }else if(admem==null){
                System.out.println("<안내> 잘못된 회원번호 입니다.");
            }

        }
    }



    // 육성관리==============================================
    public static void FosterManagement(){
        while (true) {
            System.out.println("============================육성 관리============================");
            System.out.println("1.기능 2. 몬스터 3.뒤로 가기 ");
            System.out.println("선택>");
            int ch = scanner.nextInt();
            if (ch == 1) {
                System.out.println("기능");
                FuntionManagement();
            } else if (ch == 2) {
                System.out.println("몬스터");
                MonsterManagement();
            } else if (ch == 3) {
                System.out.println("뒤로 가기");
                return;
            }
        }

    }

    // 기능 관리==============================================
    public static void FuntionManagement(){
        while (true){
            ArrayList<EventDto> eventDtos = AdminController.getInstance().eventListPrint(new EventDto());
            for (int i=0;i<eventDtos.size();i++){
                System.out.print("기능 번호:"+eventDtos.get(i).getEno()+"\t");
                System.out.print("기능 이름:"+eventDtos.get(i).getEname()+"\t");
                //System.out.print("기능 이미지:"+eventDtos.get(i).getEimg()+"\t");
                System.out.print("상승 스탯:"+eventDtos.get(i).getStatUp()+"\t");
                System.out.println("하락 스탯:"+eventDtos.get(i).getStatDown()+"\t");
            }

            System.out.println("============================이벤트 관리============================");
            System.out.println("1.이벤트 정보 수정 2.이벤트 추가 3.이벤트 삭제 4. 뒤로 가기");
            System.out.println("선택>"); int ch=scanner.nextInt();
            if (ch == 1){
                System.out.println("이벤트 번호 : "); int eno = scanner.nextInt();
                System.out.println("이벤트 명 : "); String ename = scanner.next();
                System.out.println("이벤트 이미지 " ); // BigDecimal eimg = scanner.nextBigDecimal();
                String eimg = "";
                try {
                    String str;
                    while (!(str= br.readLine()).equals("")){
                        eimg += str+"\n";
                    }

                } catch (Exception e){
                    System.out.println(e);
                }
                System.out.println("1.hp 2.stress 3.iq 4.strong");
                System.out.println("올라갈 스탯번호 : "); int statup = scanner.nextInt();
                System.out.println("1.hp 2.stress 3.iq 4.strong");
                System.out.println("내려갈 스탯번호 : "); int statdown = scanner.nextInt();
                System.out.println(eimg);
                EventDto eventDto = new EventDto(eno , ename , eimg , statup , statdown );
                boolean result = AdminController.getInstance().eventChange(eventDto);

                return;

            }
            else if (ch == 2) {
                System.out.println("이벤트 번호 : "); int eno = scanner.nextInt();
                System.out.println("이벤트 명 : "); String ename = scanner.next();
                System.out.println("이벤트 이미지 " );
                String eimg = "";
                try {
                    String str;
                    while (!(str= br.readLine()).equals("")){
                        eimg += str+"\n";
                    }

                } catch (Exception e){
                    System.out.println(e);
                }
                System.out.println("1.hp 2.stress 3.iq 4.strong");
                System.out.println("올라갈 스탯번호 : "); int statup = scanner.nextInt();
                System.out.println("1.hp 2.stress 3.iq 4.strong");
                System.out.println("내려갈 스탯번호 : "); int statdown = scanner.nextInt();
                System.out.println(eimg);
                EventDto eventDto = new EventDto(eno , ename , eimg , statup , statdown );
                boolean result = AdminController.getInstance().eventInsert(eventDto);

                return;
            }
            else if (ch == 3) {
                System.out.println("삭제할 이벤트 번호 : "); int eno = scanner.nextInt();
                EventDto eventDto = new EventDto(eno);
                boolean result=AdminController.getInstance().eventDelete(eventDto);

                return;
            }else if(ch==4){
                return;
            }
        }
    }

    // 몬스터 관리=============================================
    public  static void MonsterManagement(){
        while (true) {
            ArrayList<MonsterListDto> monsterlistDtos = AdminController.getInstance().monsterListPrint(new MonsterListDto());
            for (int i = 0; i < monsterlistDtos.size(); i++) {
                System.out.print("도감번호:" + monsterlistDtos.get(i).getLino() + "\t");
                System.out.print("진화단계:" + monsterlistDtos.get(i).getStepno());
                System.out.print("지능:" + monsterlistDtos.get(i).getIq() + "\t");
                System.out.println("힘:" + monsterlistDtos.get(i).getStrong() + "\t");
            }
            System.out.println("============================몬스터리스트 관리============================");
            System.out.println("1. 몬스터 정보 수정 2. 몬스터 추가 3. 몬스터 삭제 4. 뒤로 가기 ");
            System.out.println("선택>");
            int ch = scanner.nextInt();

            if (ch == 1) {
                monsterUpdate();
            } else if (ch == 2) {
                monsterInsert();
            } else if (ch == 3) {
                monsterDelete();
            } else if (ch == 4) {
                return;
            }
        }
    }
    // 몬스터 수정=============================================
    public static void monsterUpdate(){
        System.out.println("============================몬스터 수정============================");
        System.out.println("관리할 몬스터 번호:"); int no=scanner.nextInt();
        System.out.println("지능:");         int iq =scanner.nextInt();
        System.out.println("힘:");         int strong =scanner.nextInt();
        System.out.println("변경할 이미지:");
        String mimg = "";
        try {
            String str;
            while (!(str= br.readLine()).equals("")){
                mimg += str+"\n";
            }

        } catch (Exception e){
            System.out.println(e);
        }



        MonsterListDto monsterListDto=new MonsterListDto(no,mimg,iq,strong);

        boolean result= AdminController.getInstance().monsterUpdate(monsterListDto);

    };

    // 몬스터 추가=============================================
    public static void monsterInsert(){
        System.out.println("추가할 몬스터 번호:");
        int no = scanner.nextInt();
        while (no % 3 != 1) {
            System.out.println("적합하지 않은 번호입니다. 3의배수+1의 값을 입력해주세요");
            monsterInsert();
        }
        for (int i=0;i<3;i++) {
            System.out.printf("============================%d 번 몬스터 추가============================",no+i);
            System.out.println();
            System.out.println("지능:");
            int iq = scanner.nextInt();
            System.out.println("힘:");
            int strong = scanner.nextInt();
            System.out.println("추가할 이미지:");
            String mimg = "";
            try {
                String str;
                while (!(str = br.readLine()).equals("")) {
                    mimg += str + "\n";
                }

            } catch (Exception e) {
                System.out.println(e);
            }

            MonsterListDto monsterListDto = new MonsterListDto(no+i, mimg, iq, strong);

            boolean result = AdminController.getInstance().monsterInsert(monsterListDto);
        }

    };

    // 몬스터 삭제=============================================
    public static void monsterDelete(){
        System.out.println("============================몬스터 수정============================");
        System.out.println("관리할 몬스터 번호:"); int no=scanner.nextInt();
        MonsterListDto monsterListDto=new MonsterListDto(no);
        boolean result=AdminController.getInstance().monsterDelete(monsterListDto);

    }







}
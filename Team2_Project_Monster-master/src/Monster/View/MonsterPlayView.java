package Monster.View;

import Monster.Controller.MemberController;
import Monster.Controller.MonsterController;
import Monster.Model.Dto.EventDto;

import java.util.ArrayList;
import java.util.Scanner;

public class MonsterPlayView {

    private MonsterPlayView(){};
    private static MonsterPlayView monsterView=new MonsterPlayView();
    public static MonsterPlayView getInstance(){return monsterView;}


    public void run(){
        int mno= MemberController.getInstance().logMno;// 로그인 되어있는 회원번호
        Scanner scanner=new Scanner(System.in);

        while (true){
            boolean result = MonsterController.getInstance().monsterExistence(mno); //몬스터 보유 여부 메소드
            if(result){ // 몬스터가 없으면 알 생성으로 보내기
                MonsterView.getInstance().monsterCreate(mno); // 몬스터 알 생성하는 메소드
            }
            MonsterView.getInstance().monsterGameImg(mno); // 회원번호에 맞는 몬스터 출력

            System.out.println("0. 로그아웃");
            ArrayList<EventDto>eventDtos=MonsterController.getInstance().eventPrint(new EventDto()); //육성 기능 카테고리 배열
            for(int i=0; i< eventDtos.size();i++){                                                  // 육성 기능 카테고리 배열 출력
                System.out.print(eventDtos.get(i).getEno()+"."+eventDtos.get(i).getEname()+"\t");
            }

            System.out.print("\n선택>");
            int ch=scanner.nextInt();

            if(ch==0){
                MemberController.getInstance().logOut();    //로그아웃
                MainView.getInstance().mainView();          // 메인으로 보내기
            } else if (ch<= eventDtos.size()) {
                //System.out.println("진화");
                String eImg=MonsterController.getInstance().eventExecution(ch,mno); // 육성 기능 사진 가져오는 메소드
                System.out.println(eImg);
                if( eImg.equals("dead") ){

                    System.out.println("                                                                         \n" +
                                    "                                                                         \n" +
                                    "   __        __       ___ ___       __     ___    __  __     __    _ __  \n" +
                                    " /'_ `\\    /'__`\\   /' __` __`\\   /'__`\\  / __`\\ /\\ \\/\\ \\  /'__`\\ /\\`'__\\\n" +
                                    "/\\ \\L\\ \\  /\\ \\L\\.\\_ /\\ \\/\\ \\/\\ \\ /\\  __/ /\\ \\L\\ \\\\ \\ \\_/ |/\\  __/ \\ \\ \\/ \n" +
                                    "\\ \\____ \\ \\ \\__/.\\_\\\\ \\_\\ \\_\\ \\_\\\\ \\____\\\\ \\____/ \\ \\___/ \\ \\____\\ \\ \\_\\ \n" +
                                    " \\/___L\\ \\ \\/__/\\/_/ \\/_/\\/_/\\/_/ \\/____/ \\/___/   \\/__/   \\/____/  \\/_/ \n" +
                                    "   /\\____/                                                               \n" +
                                    "   \\_/__/                                                                "
                            );
                    return;

                }

                try {
                    Thread.sleep(500);
                }catch (Exception e){
                    System.out.println(e);
                }

                // 기능 메소드
            }
        }
    }

}

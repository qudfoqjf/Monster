package Monster.Model.Dao;

import Monster.Controller.MonsterController;
import Monster.Model.Dto.MonsterDto;
import Monster.Model.Dto.EventDto;

import Monster.Model.Dto.MonsterListDto;
import Monster.View.MainView;
import Monster.View.MemberView;

import java.util.ArrayList;
import java.util.Random;

public class MonsterDao extends Dao {
    //싱글톤
    private MonsterDao(){};// 생성자 : 객체 생성시 초기화 담당
    private static MonsterDao monsterDao=new MonsterDao();
    public static MonsterDao getInstance(){return monsterDao;}

    //===============================김민지===============================
    public boolean monsterExistence(int mno){// 몬스터 보유 여부 확인
        try {
            //1. SQL 작성
            String sql = "select mno from monster where mno = ?";
            //1. SQL 기재
            ps = conn.prepareStatement(sql);
            ps.setInt(1, mno); // sql문법내 첫번째 ?에 mno 변수 값 대입
            //1. SQL 실행
            rs = ps.executeQuery(); //질의/검색(select) 결과를 rs 인터페이스에 대입한다
            //1. SQL 처리
            if (rs.next()) {  //rs.next() : 검색 결과 테이블에서 다음레코드 이동
                //System.out.println("몬스터 있음");
                return false; //몬스터 있음
            }
        }catch (Exception e){
            System.out.println(e+"존재여부");
        }
        //5.함수종료
        return true; // 몬스터 없음
    }
    //---몬스터 랜덤 생성 및 DB에 저장
    public boolean monsterRandom(String nM, int mno){
        try {
            String sql="select stepno from monsterlist where stepno=1";

            ps=conn.prepareStatement(sql);
            rs= ps.executeQuery();
            int a=0;
            while (rs.next()){// stepno의 1번의 개수만큼 a 증가
                a++;
            }
            Random random=new Random(); // 난수 객체 생성
            int mRandom=random.nextInt(a);// 1부터 몬스터 진화 1단계 개수까지 랜덤 // 번호 랜덤 수정 필요함
            if(mRandom!=0){
                mRandom=mRandom*3+1;
            }else {
                mRandom+=1;
            }

            sql = "insert into monster(mno,lino, nickname)values(?,?,?)";
            ps = conn.prepareStatement(sql);
            // ? 매개변수 대입
            ps.setInt(1, mno); //기재된 SQL내 첫번째 ?에 값 대입
            ps.setInt(2, mRandom); //기재된 SQL내 두번째 ?에 값 대입
            ps.setString(3, nM); //기재된 SQL내 세

            int count = ps.executeUpdate(); //executeUpdate() 기재된sql 실행하고 insert된 레코드 개수 반환
            if (count == 1) {
                return true;
            }// 만약에 insert 처리된 레코드가 1개면 회원가입 성공
        } catch (Exception e) {
            System.out.println(e + "생성");
        }
        return false;
    }
    //--- 이벤트 목록 출력 메소드
    public ArrayList<EventDto>eventPrint(EventDto eventDto){
        try {
            //1. SQL 작성
            String sql = "select eno,ename from event";
            //1. SQL 기재
            ps = conn.prepareStatement(sql);
            //1. SQL 실행
            rs = ps.executeQuery(); //질의/검색(select) 결과를 rs 인터페이스에 대입한다
            //1. SQL 처리
            ArrayList<EventDto> eventDtos = new ArrayList<>();
            while (rs.next()) {
                EventDto eDto = new EventDto(
                        rs.getInt("eno"),
                        rs.getString("ename")
                );
                eventDtos.add(eDto);
            }
            return eventDtos;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    //---- 몬스터 정보 출력 메소드
    public MonsterDto monsterPrint(int mno){
        try {
            String sql="select nickname,lino,hp,stress,iq,strong from monster where  mno=? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,mno);
            rs=ps.executeQuery();
            if(rs.next()){
                MonsterDto monsterDto1=new MonsterDto(
                        rs.getString("nickname"),
                        rs.getInt("lino"),
                        rs.getInt("hp"),
                        rs.getInt("stress"),
                        rs.getInt("iq"),
                        rs.getInt("strong")
                );
                return monsterDto1;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    //----- 몬스터 현재 이미지 출력 메소드
    public String monsterImg(int level){
        try {
            String sql="select img from monsterlist where lino=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,level);
            rs=ps.executeQuery();
            if(rs.next()){
                return rs.getString("img");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    //-- 이벤트 실행 및 이미지 반환 메소드
    public String eventExecution(int ch, int mno){
        try {
            String[]stat=MonsterController.getInstance().stat;
            //1. 이벤트 숫자에 있는 업다운 숫자 불러와
            String sql="select statup,statdown,eimg from event where eno=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,ch);
            rs= ps.executeQuery();
            //System.out.println("숫자 넣어서 이벤트 업다운 불러와");
            if(rs.next()) {
                int up = rs.getInt("statup");
                int down = rs.getInt("statdown");
                String eimg = rs.getString("eimg");

                //2. 숫자에 해당하는 인덱스 이름 가져와
                String upStat = stat[up-1];
                String downStat = stat[down-1];

                //3. 첫번째 이름 +난수 / but 만약 2번이면 부호 반대로
                int upR=(int) (Math.random() * 9) + 7;
                if(up==2){
                    upR*=-1;
                }
               // System.out.println("올라갈 스탯 "+upStat+"숫자 "+upR);
                //4. 두번째 이름 -난수 / but 만약 2번이면 부호 반대로
                int downR=((int) (Math.random() * 9) + 3)*-1;
                if(down==2){
                    downR*=-1;
                }
               // System.out.println("내려갈 스탯 "+downStat+"숫자 "+downR);

                //원본 숫자 불러와
                sql="select "+upStat+", "+downStat+" from monster where mno=?";
                ps= conn.prepareCall(sql);
                ps.setInt(1,mno);
                rs= ps.executeQuery();
                if(rs.next()){
                    upR+=rs.getInt(upStat);
                    downR+=rs.getInt(downStat);
                    if(upR>100){
                        upR=100;
                    }else if(upR<0){
                        upR=0;
                    }
                    if(downR<0){
                        downR=0;
                    }else if (downR>100){
                        downR=100;
                    }
                }
                //System.out.println("원본숫자 불러와서 더했음");

                //5. 이름으로 검색해서 업데이트
                sql = "update monster set "+upStat+" = ? , "+downStat+" = ? where mno= ?";
                ps = conn.prepareStatement(sql);
                // ? 매개변수 대입
                ps.setInt(1, upR);   // 합친 숫자
                ps.setInt(2, downR); // 합친 숫자
                ps.setInt(3, mno);


                int count=ps.executeUpdate();
                if(count==1){
                    if( dead(mno) ){ return eimg="dead"; } ; // 사망 메소드
                    evolution(mno);// 진화 메소드
                    //System.out.println(" 이벤트 이미지");
                    return eimg;
                }
            }
        }catch (Exception e){
            System.out.println("민지"+e);
        }
        return null;
    }
    //================================================================

    //===============================김건우===============================

    // 진화 메소드 ==============================================================
    public int evolution(int mno) {
        try {
            // 몬스터 테이블의 mno 가 ? 일때 lino iq strong 값 줘
            String sql = "select lino , iq , strong from monster where mno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1 , mno);
            rs = ps.executeQuery();
            if (rs.next()) {
                int pluslino = rs.getInt("lino");
                int miq = rs.getInt("iq");
                int mstrong = rs.getInt("strong");


                // 몬스터리스트 테이블의 lino 가 ? 일때 iq , strong 값 줘
                String sql2 = "select * from monsterlist where lino = ?";

                ps = conn.prepareStatement(sql2);

                ps.setInt(1, (pluslino + 1));
                rs = ps.executeQuery();
                if (rs.next()) {
                    int pluslino1 = rs.getInt(1);
                    int mliq = rs.getInt(4);
                    int mlstrong = rs.getInt(5);


                    if (miq >= mliq && mstrong >= mlstrong) {
                        if (pluslino % 3 != 0) {
                            String sql3 = "update monster set lino = ? where mno = ?";
                            ps = conn.prepareStatement(sql3);
                            ps.setInt(1, pluslino1);
                            ps.setInt(2, mno);
                            int count = ps.executeUpdate();

                            if (count == 1) {
                                try {
                                    System.out.println("                          ___                __                               __     \n" +
                                            "                         /\\_ \\              /\\ \\__    __                     /\\ \\    \n" +
                                            "   __    __  __    ___   \\//\\ \\     __  __  \\ \\ ,_\\  /\\_\\     ___     ___    \\ \\ \\   \n" +
                                            " /'__`\\ /\\ \\/\\ \\  / __`\\   \\ \\ \\   /\\ \\/\\ \\  \\ \\ \\/  \\/\\ \\   / __`\\ /' _ `\\   \\ \\ \\  \n" +
                                            "/\\  __/ \\ \\ \\_/ |/\\ \\L\\ \\   \\_\\ \\_ \\ \\ \\_\\ \\  \\ \\ \\_  \\ \\ \\ /\\ \\L\\ \\/\\ \\/\\ \\   \\ \\_\\ \n" +
                                            "\\ \\____\\ \\ \\___/ \\ \\____/   /\\____\\ \\ \\____/   \\ \\__\\  \\ \\_\\\\ \\____/\\ \\_\\ \\_\\   \\/\\_\\\n" +
                                            " \\/____/  \\/__/   \\/___/    \\/____/  \\/___/     \\/__/   \\/_/ \\/___/  \\/_/\\/_/    \\/_/\n" +
                                            "                                                                                     \n" +
                                            "                                                                                     ");
                                    Thread.sleep(1000);
                                    return 1;
                                }catch (Exception e){
                                    System.out.println(e);
                                }

                            }

                        }
                    }
                }
            }




        } catch (Exception e) {
            System.out.println("건우"+e);
        }
        return 0;
    }

    // 사망 메소드 ==============================================================
    public boolean dead(int mno){
        try {
            String sql = "select mno , hp , stress from monster where mno = ?";
            //System.out.println(mno);
            ps = conn.prepareStatement(sql);
            ps.setInt(1 , mno);
            rs = ps.executeQuery();
            if (rs.next()) {
                int deadmno = rs.getInt(1);
                int hp = rs.getInt("hp");
                int stress = rs.getInt("stress");



                if (hp <= 0 || stress >= 100) {
                    String sql2 = "delete from monster where mno = ?";
                    ps = conn.prepareStatement(sql2);
                    ps.setInt(1, deadmno);
                    /*int deadmno1 = deadmno;*/
                    int count = ps.executeUpdate();

                    if (count == 1) {
                        return true;
                    }


                }


            }

        }catch (Exception e){
            System.out.println("건우"+e);
        }return false;



    }


    //===============================김건우=====================================================================================




}







  /*public boolean evolution() {
        System.out.println(mstat.toString());
        while (true) {

            // 몬스터리스트 테이블 배열 i , 몬스터 테이블 배열 j
            for (int i = 0; i < mlist.size(); i++) {
//                System.out.println(mlist.get(i).getLino());
                for (int j = 0; j < mstat.size(); j++) {
//                    System.out.println(mstat.get(j).getLino());
                    // 만약에 몬스터리스트의 리스트넘버랑 몬스터의 리스트넘버랑 같고
                    // 몬스터리스트의 지능 과 힘이 몬스터의 지능과 힘보다 같거나 작으면 진화조건 달성
                    System.out.println(mstat.size());
                    if (mlist.get(i).getLino() == mstat.get(j).getLino() && stepno != 3) {
                        if (mlist.get(i).getIq() <= mstat.get(j).getIq() && mlist.get(i).getStrong() <= mstat.get(j).getStrong()) {
                            System.out.println("진화조건 달성");
                            *//*int newlino =mstat.get(j).getLino();*//*
                            int newlino1 =mstat.get(j).getLino()+1;
                            System.out.println(newlino1);
                            // 뉴 리스트넘버에 몬스터테이블의 리스트넘버를 대입하고
                            try {
                                // 진화 조건달성시 데이터베이스 lino 1 추가
                                String sql = "update monster set lino = ? where mno = ? ";
                                ps=conn.prepareStatement(sql);
                                ps.setInt(1 , newlino1);
                                ps.setInt(2, mstat.get(j).getMno());

                                int count = ps.executeUpdate();
                                if (count == 1) {
                                    System.out.println("진화 완료: 새로운 lino = " + newlino1);
                                    return true; // 진화가 성공하면 true 반환
                                } else {
                                    System.out.println("진화에 실패하였습니다.");
                                }
                            } catch (Exception e) {
                                System.out.println("진화 중 오류 발생: " + e.getMessage());
                            }
                        }
                    }
                }
            }return false;
        }
    }*/
/*
public boolean dead() {
        for (int i = 0; i < mstat.size(); i++) {
            if (mstat.get(i).getHp() == 0 || mstat.get(i).getStress() == 100) {
                System.out.println("사망조건 달성");
                try {
                    String sql = "delete from monster where mno = ?";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, "mno");
                    System.out.println("사망 성공 띠밤");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        return false;
    }

 */
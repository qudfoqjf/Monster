package Monster.Controller;

import Monster.Model.Dao.MonsterDao;
import Monster.Model.Dto.MonsterDto;
import Monster.Model.Dto.EventDto;
import Monster.Model.Dto.MonsterListDto;

import java.util.ArrayList;


public class MonsterController {
    public String[]stat={"hp","stress","iq","strong"};
    private MonsterController() {}    ;
    private static MonsterController monsterController = new MonsterController();
    public static MonsterController getInstance() {
        return monsterController;
    }



    
    //===================김민지==========================
    //----- 몬스터 존재 확인 메소드
    public boolean monsterExistence(int mno){
         boolean result;
         result=MonsterDao.getInstance().monsterExistence(mno);
         return result;
    }
    //----몬스터 이름 유효성 메소드
    public boolean monstername(String nM){
        if(!nM.equals("")){
            return false;
        }
        return true;
    }
    //------- 몬스터 랜덤 메소드
    public boolean monsterRandom(String nM,int mno){
        boolean result=true;
        result=MonsterDao.getInstance().monsterRandom(nM,mno);
        return result;
    }
    //------ 이벤트 선택지 출력 메소드
    public ArrayList<EventDto>eventPrint(EventDto eventDto){
        ArrayList<EventDto>eDtos=MonsterDao.getInstance().eventPrint(eventDto);
        return eDtos;
    }
    //---- 몬스터 정보 출력 메소드
    public MonsterDto monsterPrint(int mno){
        return MonsterDao.getInstance().monsterPrint(mno);
    }
    //--- 몬스터 진화 이미지 출력 메소드
    public String monsterImg(int level){
        String result=MonsterDao.getInstance().monsterImg(level);
        return result;
    }
    //---이벤트 선택시 실행 및 이미지 반환 메소드
    public String eventExecution(int ch,int mno){
        String result = MonsterDao.getInstance().eventExecution(ch,mno);
        return result;
    }
    //==================================================
    // ===============================================

}


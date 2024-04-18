package Monster.Controller;

import Monster.Model.Dao.AdminDao;
import Monster.Model.Dao.MemberDao;
import Monster.Model.Dto.EventDto;
import Monster.Model.Dto.MemberDto;
import Monster.Model.Dto.MonsterDto;
import Monster.Model.Dto.MonsterListDto;

import java.util.ArrayList;

public class AdminController {
    private AdminController(){}
    private static AdminController adminController= new AdminController();

    public static  AdminController getInstance(){return adminController;}

    // 모든회원정보 출력
    public ArrayList<MemberDto>memberPrint(MemberDto memberDto){
        ArrayList<MemberDto> result = null;
        result = AdminDao.getInstance().MemberPrint(memberDto);
        return result;
    }

    //회원정보개별출력
    public MemberDto memprin(int mno){
        boolean result = AdminDao.getInstance().checkMem(mno);
        if(result){
            return AdminDao.getInstance().memprin(mno);
        }
        return null;
    }

    // 정보 수정===============================================
    public boolean  memberChange(MemberDto memberDto){
        boolean result =false;

        result=AdminDao.getInstance().memberChange(memberDto);

        return result;
    }
    // 정보 삭제=====================================================
    public int  memberDelete(int mno){
        int result =0;
        result=AdminDao.getInstance().memberDelete(mno);
        System.out.println(result);
        return result;
    }
    // 몬스터리스트 출력
    public ArrayList<MonsterListDto>monsterListPrint(MonsterListDto monsterListDto){

        ArrayList<MonsterListDto>result= null;
        result=AdminDao.getInstance().monsterListPrint(monsterListDto);
        return result;
    }
    // 몬스터리스트 수정
    public  boolean monsterUpdate(MonsterListDto monsterListDto){
        boolean result=AdminDao.getInstance().monsterUpdate(monsterListDto);
        return result;
    }
    //몬스터리스트 추가
    public boolean monsterInsert(MonsterListDto monsterListDto ){
        boolean result = AdminDao.getInstance().monsterInsert(monsterListDto);
        return result;
    }

    //몬스터 리스트에서 삭제
    public boolean monsterDelete(MonsterListDto monsterListDto){
        boolean result=AdminDao.getInstance().monsterDelete(monsterListDto);
    return result;
    }
    //이벤트리스트 출력
    public ArrayList<EventDto>eventListPrint(EventDto eventDto){

        ArrayList<EventDto>result= null;
        result=AdminDao.getInstance().eventListPrint(eventDto);
        return result;
    }

    // =================================================김건우==================================================================
    // 이벤트 수정
    public boolean eventChange(EventDto eventDto){
        boolean result = AdminDao.getInstance().eventChange(eventDto);
        return result;
    }
    // =================================================김건우==================================================================

    // 이벤트 추가
    public boolean eventInsert(EventDto eventDto){
        boolean result = AdminDao.getInstance().eventInsert(eventDto);
        return result;
    }
    // 이벤트 삭제
    public boolean eventDelete(EventDto eventDto){
        boolean result = AdminDao.getInstance().eventDelete(eventDto);
        return result;
    }

}

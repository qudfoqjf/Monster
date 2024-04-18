package Monster;

import Monster.Controller.MonsterController;
import Monster.Model.Dao.MonsterDao;
import Monster.Model.Dto.MonsterDto;
import Monster.Model.Dto.MonsterListDto;
import Monster.View.MainView;

import java.util.ArrayList;

public class App_Monster {
    public static void main(String[] args) {


        //메인화면 가져오기
        MainView.getInstance().mainView();

    }
}

package ingame.view;
import java.util.Comparator;

public class ScoreComparator implements Comparator<Player> {
	public int compare(Player score1, Player score2) {

            int sc1 = (int) score1.getHour();
            int sc2 = (int) score2.getHour();
            int sc3=(int) score1.getMinute();
            int sc4=(int) score2.getMinute();
            int sc5= (int)score1.getSecond();
            int sc6= (int)score2.getSecond();
               
               
            if (sc1 > sc2){
                return +1;
               
                	
          
            }else if (sc1 < sc2){
                return -1;
            }else{
                if(sc3<sc4) {
                	return -1;
                	
                }else if(sc3>sc4) {
                	return 1;
                }else {
                	if(sc5<sc6) {
                		return -1;
                	}else if (sc5>sc6) {
                		return 1;
                	}else {
                		return 0;
                	}
                
              
                
            }
        }
}}
import java.util.LinkedList;
import java.util.List;

public class UTPPointsCalculator {
    public static void main(String[] args) {



        List<Integer> marks = new LinkedList<Integer>();
        List<Integer> points = new LinkedList<Integer>();


        //UTP1
        marks.add(0);
        points.add(10);

        //UTP2 12/20
        marks.add(4);
        marks.add(8);

        points.add(10);
        points.add(10);


        //UTP3 0/55
        marks.add(0);
        marks.add(0);
        marks.add(0);
        marks.add(0);

        points.add(20);
        points.add(10);
        points.add(5);
        points.add(20);

        //UTP4 18/44
        marks.add(0);
        marks.add(10);
        marks.add(8);

        points.add(10);
        points.add(10);
        points.add(24);

        //UTP5 14/16
        marks.add(4);
        marks.add(10);

        points.add(6);
        points.add(10);

        //UTP6 30/30
        marks.add(10);
        marks.add(10);
        marks.add(10);

        points.add(10);
        points.add(10);
        points.add(10);

        //UTP7  8/8
        marks.add(8);
        points.add(8);

        //UTP8  25/25
        marks.add(25);
        points.add(25);

        //UTP9 10/10
        marks.add(5);
        marks.add(5);

        points.add(5);
        points.add(5);


        int marksPoints = 0;
        for (int i = 0; i < marks.size(); i++) {
            marksPoints += marks.get(i);
        }

        int pointsAll = 0;
        for (int i = 0; i < points.size(); i++) {
            pointsAll += points.get(i);
        }

        System.out.println("UTP");
        System.out.println("Punkty: " + marksPoints + " / " + pointsAll);

        if(((pointsAll/2)-marksPoints) > 0){
            System.out.println("Do zaliczenia brakuje: " + ((pointsAll/2)-marksPoints)*(-1) + " punktow");
        }else{
            System.out.println("masz zapas " + ((pointsAll/2)-marksPoints)*(-1) + " punktow");
            System.out.println("bravo, aktualnie zaliczasz uwu");
        }



    }
}
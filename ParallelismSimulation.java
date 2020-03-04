import java.util.ArrayList;
import java.util.List;

public class ParallelismSimulation {
    private String threadName;
    private int totalTime;
    private int executionTime;
    public ParallelismSimulation(String name, int totalTime){
        this.executionTime = 0;
        this.threadName = name;
        this.totalTime= totalTime;
    }
    public void setExecutionTime(int time) {
        int left = this.totalTime - this.executionTime;
        if(left >= time){
            this.executionTime += time;
            return;
        }
        this.executionTime += (left);
    }

    private  int timeSpend( int time){
        int previousExecutionValue = this.executionTime;
        this.setExecutionTime(time);
        return this.executionTime - previousExecutionValue;
    }
    public static void runningOrder(List<ParallelismSimulation> list){
        int totalTimeThreads = 0;
        for (ParallelismSimulation s: list) {
            totalTimeThreads += s.totalTime;
        }
        int[] timeInProcess = new int[list.size()];
        boolean[] done = new boolean[list.size()];
        int time = 0;
        while(time != totalTimeThreads){
            for (int i = 0; i <list.size() ; i++) {
                int quantum  =(int)(Math.random() * 10);
                int timeSpendValue = list.get(i).timeSpend(quantum);
                timeInProcess[i] += timeSpendValue;
                time += timeSpendValue;
                if(timeInProcess[i] == list.get(i).totalTime && !done[i]){
                    done[i] = true;
                    System.out.println(list.get(i).threadName + " is done in " + (timeInProcess[i] +timeSpendValue)  + " seconds");
                    continue;
                }
            }
        }

    }

    public static void main(String[] args) {
        List<ParallelismSimulation> list = new ArrayList<>();
        list.add(new ParallelismSimulation("Thread1",10 ));
        list.add(new ParallelismSimulation("Thread2",5));
        list.add(new ParallelismSimulation("Thread3",25 ));
        list.add(new ParallelismSimulation("Thread4",25 ));
        ParallelismSimulation.runningOrder(list);
    }

}


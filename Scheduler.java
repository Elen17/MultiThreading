import java.util.*;

public class Scheduler {
    private String threadName;
    private int totalTime;
    private int executionTime;
    private int enteringTime;

    Scheduler(String threadName, int totalTime, int enteringTime){
        this.threadName = threadName;
        this.enteringTime = enteringTime;
        this.totalTime = totalTime;
    }
    private void setExecutionTime(int time) {
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
    private static Scheduler nextRunning(List<Scheduler> threadList, Map<String , Boolean> checked ,int time){

        int min = time;
        Scheduler nextThread = null;
        for (int i = 0; i <threadList.size() ; i++) {
            if(threadList.get(i).enteringTime < min && !checked.get(threadList.get(i).threadName)){
                min = threadList.get(i).enteringTime;
                nextThread = threadList.get(i);
            }
        }
        if(nextThread != null){
            checked.put(nextThread.threadName, true);
        }
        return nextThread;
    }


    public static void runScheduler(List<Scheduler> threadList){
        Map<String,Boolean> checked = new HashMap<>();
        for (int i = 0; i < threadList.size(); i++) {
            checked.put(threadList.get(i).threadName, false);
        }
        Map<String, Integer> timeSpendInProcess = new HashMap<>();
        for (int i = 0; i < threadList.size(); i++) {
            timeSpendInProcess.put(threadList.get(i).threadName, 0);
        }

        Scheduler firstThread = nextRunning(threadList, checked, Integer.MAX_VALUE);

        Deque<Scheduler> queue = new ArrayDeque<>();
        if(!threadList.isEmpty()) {
            queue.add(firstThread);
        }else{
            return;
        }
        int timeInProcess = 0;

        while(!queue.isEmpty()){
            int quantum = (int) (Math.random() * 10 + 1);
            Scheduler thread = queue.removeFirst();
            int realSpendTime = thread.timeSpend(quantum);
            timeInProcess += realSpendTime;
            timeSpendInProcess.replace(thread.threadName, timeInProcess);

            Scheduler next = nextRunning(threadList, checked, timeInProcess);
            if(next != null){
                queue.addLast(next);
            }
            if(thread.totalTime == thread.executionTime){
                System.out.println(thread.threadName + " is done in "+ timeSpendInProcess.get(thread.threadName));
            }else{
                queue.addLast(thread);
            }
        }

    }
    public static void main(String[] args) {
        List<Scheduler> threadList = new ArrayList<>();
        threadList.add(new Scheduler("Thread1",20 , 0));
        threadList.add(new Scheduler("Thread2",5, 2));
        threadList.add(new Scheduler("Thread3",25, 2 ));
        threadList.add(new Scheduler("Thread4",20 , 0));
        runScheduler(threadList);
    }
}


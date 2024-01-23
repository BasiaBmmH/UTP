package zad2;

public class StringTask implements Runnable {
    private final String stringToMultiply;
    private final int times;
    private volatile String result = "";
    private volatile TaskState state;
    private Thread thread;

    public StringTask(String stringToMultiply, int times) {
        this.stringToMultiply = stringToMultiply;
        this.times = times;
        this.state = TaskState.CREATED;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            if (Thread.interrupted()) {
                state = TaskState.ABORTED;
                return;
            }
            result += stringToMultiply;
        }
        state = TaskState.READY;
    }

    public String getResult() {
        return result;
    }

    public TaskState getState() {
        return state;
    }

    public void start() {
        if (state == TaskState.CREATED) {
            state = TaskState.RUNNING;
            thread = new Thread(this);
            thread.start();
        }
    }

    public void abort() {
        if (thread != null) {
            thread.interrupt();
        }
    }

    public boolean isDone() {
        return state == TaskState.READY || state == TaskState.ABORTED;
    }

    public enum TaskState {
        CREATED, RUNNING, ABORTED, READY
    }
}
/**
 * @author Michalik Barbara S24660
 */

package zad3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.*;

public class Main {
    private DefaultListModel<FutureTask<String>> model;
    private JList<FutureTask<String>> list;
    private ExecutorService executor;

    public Main() {
        executor = Executors.newFixedThreadPool(4);
        model = new DefaultListModel<>();
        list = new JList<>(model);
        setupUI();
    }

    private void setupUI() {
        JFrame frame = new JFrame("Future Tasks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JButton startTaskButton = new JButton("Start New Task");
        startTaskButton.addActionListener(this::startNewTask);

        JButton checkStatusButton = new JButton("Check Task Status");
        checkStatusButton.addActionListener(this::checkTaskStatus);

        JButton cancelButton = new JButton("Cancel Task");
        cancelButton.addActionListener(this::cancelTask);

        JPanel panel = new JPanel();
        panel.add(startTaskButton);
        panel.add(checkStatusButton);
        panel.add(cancelButton);

        frame.add(new JScrollPane(list), BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private void startNewTask(ActionEvent e) {
        FutureTask<String> task = new FutureTask<>(() -> {
            Thread.sleep(5000);
            return "Task Completed";
        });

        model.addElement(task);
        executor.execute(task);
    }

    private void checkTaskStatus(ActionEvent e) {
        FutureTask<String> selectedTask = list.getSelectedValue();
        if (selectedTask != null) {
            if (selectedTask.isDone()) {
                try {
                    JOptionPane.showMessageDialog(null, "Task Result: " + selectedTask.get());
                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Task is still running");
            }
        }
    }

    private void cancelTask(ActionEvent e) {
        FutureTask<String> selectedTask = list.getSelectedValue();
        if (selectedTask != null && !selectedTask.isDone()) {
            selectedTask.cancel(true);
            JOptionPane.showMessageDialog(null, "Task has been cancelled");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}



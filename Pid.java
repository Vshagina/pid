package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pid {
    public static void main(String[] args) {
        List<Integer> pidList = getProcessList();
        Collections.sort(pidList);
        System.out.println("Отсортированный список PID запущенных процессов:");
        for (int pid : pidList) {
            System.out.println(pid);
        }
    }
    public static List<Integer> getProcessList() {
        List<Integer> pidList = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    int pid = Integer.parseInt(parts[1].replace('"', ' ').trim());
                    pidList.add(pid);
                }
            }
            int exitCode = process.waitFor();
        } catch (IOException | InterruptedException e) {
        }
        return pidList;
    }
}
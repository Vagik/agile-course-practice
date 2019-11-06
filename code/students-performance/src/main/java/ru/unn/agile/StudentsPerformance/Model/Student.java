package ru.unn.agile.StudentsPerformance.Model;

import javax.crypto.IllegalBlockSizeException;
import java.util.*;

public class Student {

    private final static Integer excellentMark = 5;
    private final static Integer goodMark = 4;
    private final static Integer satisfactoryMark = 3;
    private final static Integer badMark = 2;

    private List<Integer> listOfMarks;

    public Student() {
        listOfMarks = new ArrayList<>();
    }

    public Student(List<Integer> listOfMarks) {
        if (listOfMarks == null) {
            throw new IllegalArgumentException("The list of student marks can't be null");
        }
        if (listOfMarks.stream().anyMatch(mark -> mark < badMark || mark > excellentMark)) {
            throw new IllegalArgumentException("Marks must be in the range of 2 to 5");
        }
        this.listOfMarks = listOfMarks;
    }

    public Integer getMarksNumber() {
        return listOfMarks.size();
    }

    public boolean checkIsExcellent() {
        return !listOfMarks.isEmpty() &&
                listOfMarks.stream().allMatch(mark -> mark.equals(excellentMark));
    }

    public boolean checkIsGood() {
        return !listOfMarks.isEmpty() &&
                listOfMarks.stream().allMatch(mark -> mark >= goodMark) &&
                listOfMarks.stream().anyMatch(mark -> mark.equals(goodMark));
    }

    public boolean checkIsSatisfactory() {
        return !listOfMarks.isEmpty() &&
                listOfMarks.stream().allMatch(mark -> mark >= satisfactoryMark) &&
                listOfMarks.stream().anyMatch(mark -> mark.equals(satisfactoryMark));
    }

    public boolean checkIsBad() {
        return !listOfMarks.isEmpty() &&
                listOfMarks.stream().anyMatch(mark -> mark.equals(badMark));
    }

    public Double getAverageRating() {
        if (listOfMarks.isEmpty()) {
            return 0.0;
        }

        double marksCount = listOfMarks.size();
        double marksSum = listOfMarks.stream().mapToInt(Integer::intValue).sum();
        return marksSum / marksCount;
    }
}

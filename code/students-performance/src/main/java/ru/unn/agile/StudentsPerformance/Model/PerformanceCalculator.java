package ru.unn.agile.StudentsPerformance.Model;

import java.util.List;

public class PerformanceCalculator {

    private  List<Student> students;

    public PerformanceCalculator(List<Student> students) {
        if (students == null || students.isEmpty()) {
            throw new IllegalArgumentException("The list of students can't be null or empty");
        }
        this.students = students;
    }

    public Integer getExcellentStudentsNumber() {
        return (int)students.stream().filter(Student::checkIsExcellent).count();
    }

    public Integer getGoodStudentsNumber() {
        return (int)students.stream().filter(Student::checkIsGood).count();
    }

    public Integer getSatisfactoryStudentsNumber() {
        return (int)students.stream().filter(Student::checkIsSatisfactory).count();
    }

    public Integer getBadStudentsNumber() {
        return (int)students.stream().filter(Student::checkIsBad).count();
    }

    public Double getAverageRating() {
        double marksSum = students.stream().mapToDouble(Student::getAverageRating).sum();
        double studentsNumber = students.stream().filter(student -> student.getAverageRating() != 0.0).count();

        if (studentsNumber == 0) {
            throw new IllegalArgumentException("Students don't have marks");
        }
        return  marksSum / studentsNumber;
    }

    public Integer getTotalMarksNumber() {
        return students.stream().mapToInt(Student::getMarksNumber).sum();
    }

    public Double getPercentageOfTraining() {
        int certifiedStudents = getExcellentStudentsNumber() + getGoodStudentsNumber() + getSatisfactoryStudentsNumber();
        double studentsNumber = students.stream().filter(student -> student.getAverageRating() != 0.0).count();

        if (studentsNumber == 0) {
            throw new IllegalArgumentException("Students don't have marks");
        }
        return certifiedStudents / studentsNumber * 100;
    }
}

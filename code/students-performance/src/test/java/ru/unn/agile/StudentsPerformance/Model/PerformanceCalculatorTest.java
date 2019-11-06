package ru.unn.agile.StudentsPerformance.Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PerformanceCalculatorTest {

    private static final double compareDelta = 0.001;

    @Test
    public void canCreateCalculator() {
        List<Student> students = new ArrayList<>(
                List.of(
                        new Student(new ArrayList<>(List.of(5, 3, 2, 5, 4, 4))),
                        new Student(new ArrayList<>(List.of(5, 5, 5, 5, 5, 5)))
                        )
                );
        PerformanceCalculator calculator = new PerformanceCalculator(students);
        assertNotNull(calculator);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCreateCalculatorIfStudentsAreNull() {
        List<Student> students = null;
        new PerformanceCalculator(students);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canCreateCalculatorIfStudentsAreEmpty() {
        List<Student> students = new ArrayList<>();
        new PerformanceCalculator(students);
    }

    @Test
    public void canGetExcellentStudentsNumberIfExist() {
        List<Student> students = new ArrayList<>(
                List.of(
                        new Student(new ArrayList<>(List.of(5, 3, 2, 5, 4, 4))),
                        new Student(new ArrayList<>(List.of(5, 5, 5, 5, 5, 5))),
                        new Student(new ArrayList<>(List.of(3, 2, 4, 5, 2, 5))),
                        new Student(new ArrayList<>(List.of(5, 5, 5, 5, 5, 5))),
                        new Student(new ArrayList<>(List.of(3, 5, 4, 4, 4, 3)))
                )
        );
        PerformanceCalculator calculator = new PerformanceCalculator(students);
        assertEquals(2, calculator.getExcellentStudentsNumber(), compareDelta);
    }

    @Test
    public void canGetExcellentStudentsNumberIfNotExist() {
        List<Student> students = new ArrayList<>(
                List.of(
                        new Student(new ArrayList<>(List.of(5, 3, 2, 5, 4, 4))),
                        new Student(new ArrayList<>(List.of(5, 3, 5, 2, 5, 5))),
                        new Student(new ArrayList<>(List.of(3, 2, 4, 5, 2, 5))),
                        new Student(new ArrayList<>(List.of(5, 4, 5, 4, 5, 5))),
                        new Student(new ArrayList<>(List.of(3, 5, 4, 4, 4, 3)))
                )
        );
        PerformanceCalculator calculator = new PerformanceCalculator(students);
        assertEquals(0, calculator.getExcellentStudentsNumber(), compareDelta);
    }

    @Test
    public void canGetGoodStudentsNumberIfExist() {
        List<Student> students = new ArrayList<>(
                List.of(
                        new Student(new ArrayList<>(List.of(5, 3, 2, 5, 4, 4))),
                        new Student(new ArrayList<>(List.of(4, 4, 5, 5, 5, 5))),
                        new Student(new ArrayList<>(List.of(3, 2, 4, 5, 2, 5))),
                        new Student(new ArrayList<>(List.of(5, 5, 5, 5, 5, 5))),
                        new Student(new ArrayList<>(List.of(3, 5, 4, 4, 4, 3)))
                )
        );
        PerformanceCalculator calculator = new PerformanceCalculator(students);
        assertEquals(1, calculator.getGoodStudentsNumber(), compareDelta);
    }

    @Test
    public void canGetGoodStudentsNumberIfNotExist() {
        List<Student> students = new ArrayList<>(
                List.of(
                        new Student(new ArrayList<>(List.of(5, 3, 2, 5, 4, 4))),
                        new Student(new ArrayList<>(List.of(5, 3, 5, 2, 5, 5))),
                        new Student(new ArrayList<>(List.of(3, 2, 4, 5, 2, 5))),
                        new Student(new ArrayList<>(List.of(5, 5, 5, 5, 5, 5))),
                        new Student(new ArrayList<>(List.of(3, 5, 4, 4, 4, 3)))
                )
        );
        PerformanceCalculator calculator = new PerformanceCalculator(students);
        assertEquals(0, calculator.getGoodStudentsNumber(), compareDelta);
    }

    @Test
    public void canGetSatisfactoryStudentsNumberIfExist() {
        List<Student> students = new ArrayList<>(
                List.of(
                        new Student(new ArrayList<>(List.of(5, 3, 2, 5, 4, 4))),
                        new Student(new ArrayList<>(List.of(4, 4, 5, 5, 3, 5))),
                        new Student(new ArrayList<>(List.of(3, 2, 4, 5, 2, 5))),
                        new Student(new ArrayList<>(List.of(5, 5, 5, 5, 5, 5))),
                        new Student(new ArrayList<>(List.of(3, 5, 4, 4, 4, 3)))
                )
        );
        PerformanceCalculator calculator = new PerformanceCalculator(students);
        assertEquals(2, calculator.getSatisfactoryStudentsNumber(), compareDelta);
    }

    @Test
    public void canGetSatisfactoryStudentsNumberIfNotExist() {
        List<Student> students = new ArrayList<>(
                List.of(
                        new Student(new ArrayList<>(List.of(5, 3, 2, 5, 4, 4))),
                        new Student(new ArrayList<>(List.of(5, 3, 5, 2, 5, 5))),
                        new Student(new ArrayList<>(List.of(3, 2, 4, 5, 2, 5))),
                        new Student(new ArrayList<>(List.of(5, 5, 5, 5, 5, 5))),
                        new Student(new ArrayList<>(List.of(5, 5, 4, 4, 4, 4)))
                )
        );
        PerformanceCalculator calculator = new PerformanceCalculator(students);
        assertEquals(0, calculator.getSatisfactoryStudentsNumber(), compareDelta);
    }

    @Test
    public void canGetBadStudentsNumberIfExist() {
        List<Student> students = new ArrayList<>(
                List.of(
                        new Student(new ArrayList<>(List.of(5, 3, 2, 5, 4, 4))),
                        new Student(new ArrayList<>(List.of(4, 4, 5, 5, 3, 5))),
                        new Student(new ArrayList<>(List.of(3, 2, 4, 5, 2, 5))),
                        new Student(new ArrayList<>(List.of(5, 5, 5, 5, 5, 5))),
                        new Student(new ArrayList<>(List.of(3, 5, 2, 4, 4, 3)))
                )
        );
        PerformanceCalculator calculator = new PerformanceCalculator(students);
        assertEquals(3, calculator.getBadStudentsNumber(), compareDelta);
    }

    @Test
    public void canGetBadStudentsNumberIfNotExist() {
        List<Student> students = new ArrayList<>(
                List.of(
                        new Student(new ArrayList<>(List.of(5, 3, 3, 5, 4, 4))),
                        new Student(new ArrayList<>(List.of(5, 3, 5, 4, 5, 5))),
                        new Student(new ArrayList<>(List.of(3, 4, 4, 5, 3, 5))),
                        new Student(new ArrayList<>(List.of(5, 5, 5, 5, 5, 5))),
                        new Student(new ArrayList<>(List.of(5, 5, 4, 4, 4, 4)))
                )
        );
        PerformanceCalculator calculator = new PerformanceCalculator(students);
        assertEquals(0, calculator.getBadStudentsNumber(), compareDelta);
    }

    @Test
    public void canGetStudentsAverageRating() {
        List<Student> students = new ArrayList<>(
                List.of(
                        new Student(new ArrayList<>(List.of(5, 3, 2, 5, 4, 4))),
                        new Student(new ArrayList<>(List.of(5, 5, 5, 5, 5, 5))),
                        new Student(new ArrayList<>(List.of(3, 2, 4, 5, 2, 5))),
                        new Student(new ArrayList<>(List.of(5, 5, 5, 5, 5, 5))),
                        new Student(new ArrayList<>(List.of(3, 5, 4, 4, 4, 3)))
                )
        );
        PerformanceCalculator calculator = new PerformanceCalculator(students);
        assertEquals(4.233, calculator.getAverageRating(), compareDelta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canGetStudentsAverageRatingIfAllStudentsAreEmpty() {
        List<Student> students = new ArrayList<>(
                List.of(
                        new Student(),
                        new Student(),
                        new Student(),
                        new Student(),
                        new Student()
                )
        );
        PerformanceCalculator calculator = new PerformanceCalculator(students);
        calculator.getAverageRating();
    }

    @Test
    public void canGetStudentsAverageRatingIfIsEmptyStudent() {
        List<Student> students = new ArrayList<>(
                List.of(
                        new Student(new ArrayList<>(List.of(5, 3, 2, 5, 4, 4))),
                        new Student(),
                        new Student(new ArrayList<>(List.of(3, 2, 4, 5, 2, 5))),
                        new Student(new ArrayList<>(List.of(5, 5, 5, 5, 5, 5))),
                        new Student(new ArrayList<>(List.of(3, 5, 4, 4, 4, 3)))
                )
        );
        PerformanceCalculator calculator = new PerformanceCalculator(students);
        assertEquals(4.042, calculator.getAverageRating(), compareDelta);
    }

    @Test
    public void canGetTotalMarksNumber() {
        List<Student> students = new ArrayList<>(
                List.of(
                        new Student(new ArrayList<>(List.of(5, 3, 2, 5, 4))),
                        new Student(),
                        new Student(new ArrayList<>(List.of(3, 2, 4, 5, 2, 5))),
                        new Student(new ArrayList<>(List.of(5, 5, 5, 5, 5, 5, 4))),
                        new Student(new ArrayList<>(List.of(3, 5, 4)))
                )
        );
        PerformanceCalculator calculator = new PerformanceCalculator(students);
        assertEquals(21, calculator.getTotalMarksNumber(), compareDelta);
    }

    @Test
    public void canGetTotalMarksNumberIfNoMarks() {
        List<Student> students = new ArrayList<>(
                List.of(
                        new Student(),
                        new Student(),
                        new Student(),
                        new Student(),
                        new Student()
                )
        );
        PerformanceCalculator calculator = new PerformanceCalculator(students);
        assertEquals(0, calculator.getTotalMarksNumber(), compareDelta);
    }

    @Test
    public void canGetPercentageOfTraining() {
        List<Student> students = new ArrayList<>(
                List.of(
                        new Student(new ArrayList<>(List.of(5, 3, 2, 5, 4))),
                        new Student(),
                        new Student(new ArrayList<>(List.of(3, 2, 4, 5, 2, 5))),
                        new Student(new ArrayList<>(List.of(5, 5, 5, 5, 5, 5, 4))),
                        new Student(new ArrayList<>(List.of(5, 5))),
                        new Student(new ArrayList<>(List.of(5, 5, 5, 5, 5, 5, 4, 2, 3, 5, 2))),
                        new Student(new ArrayList<>(List.of(3, 5, 4)))
                )
        );
        PerformanceCalculator calculator = new PerformanceCalculator(students);
        assertEquals(50.0, calculator.getPercentageOfTraining(), compareDelta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canGetPercentageOfTrainingIfNoMarks() {
        List<Student> students = new ArrayList<>(
                List.of(
                        new Student(),
                        new Student(),
                        new Student(),
                        new Student(),
                        new Student()
                )
        );
        PerformanceCalculator calculator = new PerformanceCalculator(students);
        calculator.getPercentageOfTraining();
    }
}

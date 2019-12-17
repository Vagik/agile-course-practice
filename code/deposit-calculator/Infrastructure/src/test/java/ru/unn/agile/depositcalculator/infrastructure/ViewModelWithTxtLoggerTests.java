package ru.unn.agile.depositcalculator.infrastructure;

import ru.unn.agile.depositcalculator.viewmodel.ViewModel;
import ru.unn.agile.depositcalculator.viewmodel.ViewModelTest;

public class ViewModelWithTxtLoggerTests extends ViewModelTest {
    @Override
    public void setUp() {
        TxtLogger realLogger = new TxtLogger("./ViewModelWithTxtLoggerTests.log");
        super.setViewModel(new ViewModel(realLogger));
    }
}

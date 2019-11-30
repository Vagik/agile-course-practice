package ru.unn.agile.datastructure.set.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void ifNoEnteredItemsAddButtonIsDisabled() {
        assertTrue(viewModel.isAddButtonDisabled().get());
    }

    @Test
    public void ifNoEnteredItemsRemoveButtonIsDisabled() {
        assertTrue(viewModel.isRemoveButtonDisabled().get());
    }

    @Test
    public void ifNoEnteredItemsContainsButtonIsDisabled() {
        assertTrue(viewModel.isContainsButtonDisabled().get());
    }

    @Test
    public void ifNoEnteredItemsRetainButtonIsDisabled() {
        assertTrue(viewModel.isRetainButtonDisabled().get());
    }

    @Test
    public void ifEnteredItemIsValidAddButtonIsEnabled() {
        viewModel.getTxtEnteredItemsProp().setValue("10");
        assertFalse(viewModel.isAddButtonDisabled().get());
    }

    @Test
    public void ifEnteredItemIsNotValidAddButtonIsDisabled() {
        viewModel.getTxtEnteredItemsProp().setValue("ds");
        assertTrue(viewModel.isAddButtonDisabled().get());
    }

    @Test
    public void ifEnteredItemsAreValidAddButtonIsEnabled() {
        viewModel.getTxtEnteredItemsProp().setValue("10 52 -3 95");
        assertFalse(viewModel.isAddButtonDisabled().get());
    }

    @Test
    public void ifEnteredItemsAreNotValidAddButtonIsDisabled() {
        viewModel.getTxtEnteredItemsProp().setValue("50 d 98 3s");
        assertTrue(viewModel.isAddButtonDisabled().get());
    }

    @Test
    public void canAddItemsToSet() {
        viewModel.getTxtEnteredItemsProp().setValue("12 6 92 4");
        viewModel.addEnteredItems();
        assertEquals("12 6 92 4", viewModel.getTxtCurrentSetProp().getValue());
    }

    @Test
    public void canRemoveItemsFromSet() {
        viewModel.getTxtEnteredItemsProp().setValue("2 16 9 4");
        viewModel.addEnteredItems();
        viewModel.getTxtEnteredItemsProp().setValue("2 9");
        viewModel.removeEnteredItems();
        assertEquals("16 4", viewModel.getTxtCurrentSetProp().getValue());
    }

    @Test
    public void canRetainItemsFormSet() {
        viewModel.getTxtEnteredItemsProp().setValue("5 96 35 21");
        viewModel.addEnteredItems();
        viewModel.getTxtEnteredItemsProp().setValue("21 96 3");
        viewModel.retainEnteredItems();
        assertEquals("96 21", viewModel.getTxtCurrentSetProp().getValue());
    }

    @Test
    public void canClearCurrentSet() {
        viewModel.getTxtEnteredItemsProp().setValue("5 96 35 21");
        viewModel.addEnteredItems();
        viewModel.clearCurrentItems();
        assertEquals("", viewModel.getTxtCurrentSetProp().getValue());
    }

    @Test
    public void canCheckItemsInSetIfContain() {
        viewModel.getTxtEnteredItemsProp().setValue("5 96 35 21");
        viewModel.addEnteredItems();
        viewModel.getTxtEnteredItemsProp().setValue("21 96 5");
        viewModel.containsEnteredItems();
        assertEquals("Yes", viewModel.getTxtContainsItemProp().getValue());
    }

    @Test
    public void canCheckItemsInSetIfNotContain() {
        viewModel.getTxtEnteredItemsProp().setValue("5 9 3 1");
        viewModel.addEnteredItems();
        viewModel.getTxtEnteredItemsProp().setValue("5 4 3");
        viewModel.containsEnteredItems();
        assertEquals("No", viewModel.getTxtContainsItemProp().getValue());
    }

    @Test
    public void canCheckSetIsEmptyIfEmpty() {
        viewModel.isCurrentSetEmpty();
        assertEquals("Yes", viewModel.getTxtIsEmptyItemProp().getValue());
    }

    @Test
    public void canCheckSetIsEmptyIfNotEmpty() {
        viewModel.getTxtEnteredItemsProp().setValue("5 4");
        viewModel.addEnteredItems();
        viewModel.isCurrentSetEmpty();
        assertEquals("No", viewModel.getTxtIsEmptyItemProp().getValue());
    }
}

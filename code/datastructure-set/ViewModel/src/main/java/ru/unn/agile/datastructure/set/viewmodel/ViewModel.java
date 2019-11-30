package ru.unn.agile.datastructure.set.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.datastructure.set.model.MySet;

import java.util.LinkedHashSet;
import java.util.Set;

public class ViewModel {

    private BooleanProperty btnAddEnabledProp = new SimpleBooleanProperty();
    private BooleanProperty btnRemoveEnabledProp = new SimpleBooleanProperty();
    private BooleanProperty btnRetainEnabledProp = new SimpleBooleanProperty();
    private BooleanProperty btnContainsEnabledProp = new SimpleBooleanProperty();
    private StringProperty txtContainsItemProp = new SimpleStringProperty();
    private StringProperty txtIsEmptySetProp = new SimpleStringProperty();
    private StringProperty txtEnteredItemsProp = new SimpleStringProperty();
    private StringProperty txtCurrentSetProp = new SimpleStringProperty();
    private MySet<Integer> currentSet = new MySet<>();
    private Set<Integer> enteredSet = new LinkedHashSet<>();

    public ViewModel() {
        btnAddEnabledProp.setValue(false);
        btnRemoveEnabledProp.setValue(false);
        btnRetainEnabledProp.setValue(false);
        btnContainsEnabledProp.setValue(false);

        txtContainsItemProp.setValue("");
        txtIsEmptySetProp.setValue("");
        txtEnteredItemsProp.setValue("");
        txtCurrentSetProp.setValue("");

        txtEnteredItemsProp.addListener((observable, oldValue, newValue) -> {
            setEnteredItems(newValue);
        });
    }

    public boolean isAddButtonEnable() {
        return btnAddEnabledProp.get();
    }

    public boolean isRemoveButtonEnable() {
        return btnRemoveEnabledProp.get();
    }

    public boolean isContainsButtonEnable() {
        return btnContainsEnabledProp.get();
    }

    public boolean isRetainButtonEnable() {
        return btnRetainEnabledProp.get();
    }

    public StringProperty getTxtEnteredItemsProp() {
        return txtEnteredItemsProp;
    }

    public StringProperty getTxtCurrentSetProp() {
        return txtCurrentSetProp;
    }

    public StringProperty getTxtContainsItemProp() {
        return txtContainsItemProp;
    }

    public StringProperty getTxtIsEmptyItemProp() {
        return txtIsEmptySetProp;
    }

    private void setEnteredItems(String itemsStr) {
        enteredSet.clear();
        txtEnteredItemsProp.setValue(itemsStr);

        var isValidEnteredData = false;

        if (!itemsStr.isEmpty()) {
            var splitedItems = itemsStr.split(" ");
            if (splitedItems.length > 0) {
                for (String splitedItem : splitedItems) {
                    try {
                        int parsedItem = Integer.parseInt(splitedItem);
                        enteredSet.add(parsedItem);
                    } catch (NumberFormatException e) {
                        break;
                    }
                }
                isValidEnteredData = splitedItems.length == enteredSet.size();
            }
        }

        btnAddEnabledProp.setValue(isValidEnteredData);
        btnRemoveEnabledProp.setValue(isValidEnteredData);
        btnRetainEnabledProp.setValue(isValidEnteredData);
        btnContainsEnabledProp.setValue(isValidEnteredData);
    }

    private String getCurrentSetItems() {
        if (currentSet.isEmpty()) {
            return "";
        }
        var builder = new StringBuilder();
        var iterator = currentSet.iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next());
            builder.append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public void addEnteredItems() {
        currentSet.addAll(enteredSet);
        txtCurrentSetProp.setValue(getCurrentSetItems());
    }

    public void removeEnteredItems() {
        currentSet.removeAll(enteredSet);
        txtCurrentSetProp.setValue(getCurrentSetItems());
    }

    public void retainEnteredItems() {
        currentSet.retainAll(enteredSet);
        txtCurrentSetProp.setValue(getCurrentSetItems());
    }

    public void clearCurrentItems() {
        currentSet.clear();
        txtCurrentSetProp.setValue(getCurrentSetItems());
    }

    public void containsEnteredItems() {
        if (currentSet.containsAll(enteredSet)) {
            txtContainsItemProp.setValue("Yes");
        }
        else {
            txtContainsItemProp.setValue("No");
        }
    }

    public void isCurrentSetEmpty() {
        if (currentSet.isEmpty()) {
            txtIsEmptySetProp.setValue("Yes");
        }
        else {
            txtIsEmptySetProp.setValue("No");
        }
    }
}

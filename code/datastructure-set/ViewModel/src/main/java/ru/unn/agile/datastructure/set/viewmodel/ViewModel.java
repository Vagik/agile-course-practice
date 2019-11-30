package ru.unn.agile.datastructure.set.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.datastructure.set.model.MySet;

import java.util.LinkedHashSet;
import java.util.Set;

public class ViewModel {

    private BooleanProperty btnAddDisabledProp = new SimpleBooleanProperty();
    private BooleanProperty btnRemoveDisabledProp = new SimpleBooleanProperty();
    private BooleanProperty btnRetainDisabledProp = new SimpleBooleanProperty();
    private BooleanProperty btnContainsDisabledProp = new SimpleBooleanProperty();
    private StringProperty txtContainsItemProp = new SimpleStringProperty();
    private StringProperty txtIsEmptySetProp = new SimpleStringProperty();
    private StringProperty txtEnteredItemsProp = new SimpleStringProperty();
    private StringProperty txtCurrentSetProp = new SimpleStringProperty();
    private MySet<Integer> currentSet = new MySet<>();
    private Set<Integer> enteredSet = new LinkedHashSet<>();

    public ViewModel() {
        btnAddDisabledProp.setValue(true);
        btnRemoveDisabledProp.setValue(true);
        btnRetainDisabledProp.setValue(true);
        btnContainsDisabledProp.setValue(true);

        txtContainsItemProp.setValue("");
        txtIsEmptySetProp.setValue("");
        txtEnteredItemsProp.setValue("");
        txtCurrentSetProp.setValue("");

        txtEnteredItemsProp.addListener((observable, oldValue, newValue) -> {
            setEnteredItems(newValue);
        });
    }

    public BooleanProperty isAddButtonDisabled() {
        return btnAddDisabledProp;
    }

    public BooleanProperty isRemoveButtonDisabled() {
        return btnRemoveDisabledProp;
    }

    public BooleanProperty isContainsButtonDisabled() {
        return btnContainsDisabledProp;
    }

    public BooleanProperty isRetainButtonDisabled() {
        return btnRetainDisabledProp;
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
        } else {
            txtContainsItemProp.setValue("No");
        }
    }

    public void isCurrentSetEmpty() {
        if (currentSet.isEmpty()) {
            txtIsEmptySetProp.setValue("Yes");
        } else {
            txtIsEmptySetProp.setValue("No");
        }
    }

    private void setEnteredItems(final String itemsStr) {
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

        btnAddDisabledProp.setValue(!isValidEnteredData);
        btnRemoveDisabledProp.setValue(!isValidEnteredData);
        btnRetainDisabledProp.setValue(!isValidEnteredData);
        btnContainsDisabledProp.setValue(!isValidEnteredData);
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
}

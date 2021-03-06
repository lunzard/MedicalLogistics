package logic.commands.diet.dietmanager;


import Medication.Medicationmanager.Medication;
import logic.parser.MedicationManagerParser;
import logic.commands.Command;
import logic.commands.CommandResult;
import storage.Storage;

import java.io.IOException;
import java.util.HashMap;

import static ui.diet.dietmanager.MedicationManagerUi.DIET_IO_WRONG_FORMAT;
import static ui.diet.dietmanager.MedicationManagerUi.DIET_NEW_SUCCESS;
import static ui.diet.dietmanager.MedicationManagerUi.EMPTY_STRING;

public class MedicationSessionCreate extends Command {

    private final MedicationManagerParser parser = new MedicationManagerParser();

    @Override
    public CommandResult execute(String input, Storage storage) {
        String result = EMPTY_STRING;
        try {
            StringBuilder message = new StringBuilder();
            HashMap<String, String> parsedParams = parser.extractDietManagerCommandNameAndQuantity("new", input);
            // extract the date and tags and assigns it to the string
            String name = parser.extractNewName(parsedParams, message);
            int quantity = parser.extractNewQuantity(parsedParams, message);
            if (message.length() != 0) {
                ui.showToUser(message.toString().trim());
            }
            Medication med = new Medication(name, quantity);
            med.start(true, -1);
            result = DIET_NEW_SUCCESS;
        } catch (IOException e) {
            result = DIET_IO_WRONG_FORMAT;
        }
        return new CommandResult(result);
    }
}

package shop;

import java.util.HashMap;
import java.util.List;
import shop.service.CsvReader;
import shop.service.CsvWriter;
import shop.service.UpdateDbService;
import shop.service.Validator;
import shop.service.action.ActionHandler;
import shop.service.action.DecreaseActionHandler;
import shop.service.action.IncreaseActionHandler;
import shop.service.impl.CsvReaderImpl;
import shop.service.impl.CsvWriterImpl;
import shop.service.impl.UpdateDbServiceImpl;
import shop.service.impl.ValidatorImpl;

public class FruitShop {
    private static final String INPUT_FILE_NAME = "src\\main\\resources\\input.csv";
    private static final String OUTPUT_FILE_NAME = "src\\main\\resources\\output.csv";

    public static void main(String[] args) {
        HashMap<String, ActionHandler> actionMap = new HashMap<>();
        actionMap.put("b", new IncreaseActionHandler());
        actionMap.put("s", new IncreaseActionHandler());
        actionMap.put("r", new IncreaseActionHandler());
        actionMap.put("p", new DecreaseActionHandler());

        UpdateDbService updateDbService = new UpdateDbServiceImpl(actionMap);
        CsvReader csvReader = new CsvReaderImpl();
        CsvWriter csvWriter = new CsvWriterImpl();
        Validator validator = new ValidatorImpl();

        List<String> parsedData = csvReader.read(INPUT_FILE_NAME);
        if (validator.valid(parsedData)) {
            updateDbService.updateStorage(parsedData);
            csvWriter.write(OUTPUT_FILE_NAME);
        }
    }

}
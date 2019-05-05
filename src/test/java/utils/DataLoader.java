package utils;

import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.validation.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class DataLoader {
    private final static String DATA_DIR = "data";

    private static File getDataFile(String fileName) {
        String resourcePath = DATA_DIR + "/" + fileName;

        String fullPath;
        try {
            fullPath = Objects.requireNonNull(DataLoader.class.getClassLoader().getResource(resourcePath)).getFile();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Missing data file: " + resourcePath);
        }
        return new File(fullPath);
    }

    public static <T> List<T> getDataList(String filename, Class<T> valueType) {
        String extension = getFileExtension(filename);

        ObjectMapper mapper;
        FormatSchema schema = null;

        switch (extension) {
            case "csv":
                mapper = new CsvMapper();
                schema = CsvSchema.emptySchema().withHeader();
                break;
            case "yml":
                mapper = new ObjectMapper(new YAMLFactory());
                break;
            default:
                throw new IllegalArgumentException("Unknown data file extension '" + extension + "' for file: " + filename);
        }
        mapper.registerModule(new JavaTimeModule());

        try {
            MappingIterator<T> iter = mapper.readerFor(valueType)
                    .with(schema)
                    .readValues(getDataFile(filename));
            return iter.readAll();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static <T> T getData(String filename, Class<T> valueType) throws IOException {
        String extension = getFileExtension(filename);

        ObjectMapper mapper;

        switch (extension) {
            case "csv":
                throw new IllegalArgumentException("User 'getDataList' for CSV file: " + filename);
            case "yml":
                mapper = new ObjectMapper(new YAMLFactory());
                break;
            default:
                throw new IllegalArgumentException("Unknown data file extension '" + extension + "' for file: " + filename);
        }

        mapper.registerModule(new JavaTimeModule());
        File file = getDataFile(filename);

        T object = mapper.readValue(file, valueType);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (violations.size() > 0) {
            List<String> errors = new ArrayList<>();
            for (ConstraintViolation<T> violation : violations) {
                String error = String.format(
                        " - %s: %s but was: %s",
                        violation.getPropertyPath(),
                        violation.getMessage(),
                        violation.getInvalidValue()
                );
                errors.add(error);
            }
            Collections.sort(errors);
            throw new ValidationException("Invalid data file.\n" + "File: " + filename + "\nModel: " + valueType + "\nProblems (" + violations.size() + "):\n" + String.join("\n", errors));
        }
        return object;
    }

    private static String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }

    public void validateModel(Object model) {

    }
}


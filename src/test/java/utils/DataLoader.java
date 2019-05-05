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

    public static <T> List<T> getDataList(String filename, Class<T> valueType) throws Exception {
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

        MappingIterator<T> iter = mapper.readerFor(valueType)
                .with(schema)
                .readValues(getDataFile(filename));
        List<T> objects = iter.readAll();

        List<String> validationErrors = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            List<String> modelErrors = validateModel(objects.get(i));
            if (modelErrors.size() > 0) {
                validationErrors.add("Item " + i + " problems (" + modelErrors.size() + "):\n" + String.join("\n", modelErrors));
            }
        }

        if (validationErrors.size() > 0) {
            throw new ValidationException("Invalid data file.\n" + "File: " + filename + "\nModel: " + valueType + "\n" + String.join("\n", validationErrors));
        }

        return objects;
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
        List<String> validationErrors = validateModel(object);
        if (validationErrors.size() > 0) {
            throw new ValidationException("Invalid data file.\n" + "File: " + filename + "\nModel: " + valueType + "\nProblems (" + validationErrors.size() + "):\n" + String.join("\n", validationErrors));
        }
        return object;
    }

    private static String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }

    private static <T> List<String> validateModel(T model) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(model);

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

        return errors;
    }
}


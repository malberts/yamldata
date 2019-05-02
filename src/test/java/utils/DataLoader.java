package utils;

import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import models.Person;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataLoader {
    private final static String DATA_DIR = "data";

    private static InputStream getDataStream(String fileName) {
        return DataLoader.class.getClassLoader().getResourceAsStream(DATA_DIR + "/" + fileName);
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
                    .readValues(DataLoader.getDataStream(filename));
            return iter.readAll();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static <T> T getData(String filename, Class<T> valueType) {
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

        T object = null;
        try {
            object = mapper.readValue(getDataStream(filename), valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    private static String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }
}


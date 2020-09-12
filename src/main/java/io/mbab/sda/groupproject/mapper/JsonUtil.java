package io.mbab.sda.groupproject.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mbab.sda.groupproject.entity.CrudEntites;
import io.mbab.sda.groupproject.entity.Player;
import org.apache.catalina.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonUtil {

  private static final ObjectMapper mapper = new ObjectMapper();

  public static void writeToJsonFile(Object o, String path) throws IOException {

    // Object to JSON in file
    mapper.writeValue(new File(path), o);
  }

  // Object to JSON in String
  public static String toConvertToJson(Object o) throws JsonProcessingException {

    return mapper.writeValueAsString(o);
  }

  public static List<Player> readFromJsonFile(String path) throws IOException {

    return mapper.readValue(new File(path), new TypeReference<>() {
    });
  }
}

package io.mbab.sda.groupproject.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mbab.sda.groupproject.entity.CrudEntites;
import io.mbab.sda.groupproject.entity.Player;
import org.apache.catalina.User;

import java.io.File;
import java.io.IOException;

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

  public static void readFromJsonFile(String path, Class<Player> userClass) throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    Object object = mapper.readValue(new File(path), userClass);
  }

}

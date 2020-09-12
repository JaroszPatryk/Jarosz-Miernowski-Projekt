package io.mbab.sda.groupproject.menu.View;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mbab.sda.groupproject.entity.CrudEntites;
import io.mbab.sda.groupproject.entity.Player;
import io.mbab.sda.groupproject.mapper.JsonUtil;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.action.MainAction;
import io.mbab.sda.groupproject.menu.action.MenuAction;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class ReadPlayersFromJsonFile implements MenuAction {

    CustomScanner cs = new CustomScanner();

    @Override
    public void execute() {

        ObjectMapper mapper = new ObjectMapper();

        System.out.println("!!! Wczytujesz z pliku Json !!!");
        System.out.println("Podaj ścieżke do pliku");
        String path = cs.nextLine();


        try {
            JsonUtil.readFromJsonFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.autoportaldupe.scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class GetChestPos {
    public static double[] getKitCoordinates(String kitName) {
        try {
            File file = new File("chests.json");
            ObjectMapper mapper = new ObjectMapper();

            if (!file.exists()) return null;

            List<Map<String, String>> chests = mapper.readValue(file, List.class);

            for (Map<String, String> chest : chests) {
                if (kitName.equals(chest.get("kitname"))) {
                    double x = Double.parseDouble(chest.get("x"));
                    double y = Double.parseDouble(chest.get("y"));
                    double z = Double.parseDouble(chest.get("z"));
                    return new double[]{x, y, z};
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static double[] getMainCords(String kitName) {
        try {
            File file = new File("autoportaldupe.json");
            ObjectMapper mapper = new ObjectMapper();

            if (!file.exists()) return null;

            List<Map<String, String>> chests = mapper.readValue(file, List.class);

            for (Map<String, String> chest : chests) {
                if (kitName.equals(chest.get("kitname"))) {
                    double x = Double.parseDouble(chest.get("x"));
                    double y = Double.parseDouble(chest.get("y"));
                    double z = Double.parseDouble(chest.get("z"));
                    return new double[]{x, y, z};
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

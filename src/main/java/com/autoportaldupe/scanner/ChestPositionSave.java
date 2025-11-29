package com.autoportaldupe.scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.sun.jdi.connect.Connector;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChestPositionSave {
        public static void addChest(String kitname, double x, double z, double y) {
            try {

                double posX =(int) x;
                double posZ =(int) z;
                double posY =(int) y;

                ObjectMapper mapper = new ObjectMapper();
                File file = new File("chests.json");

                List<Map<String, String>> chests = new ArrayList<>();
                if (file.exists()) {
                    chests = mapper.readValue(file, List.class);
                }

                Map<String, String> chest = new HashMap<>();
                chest.put("kitname", kitname);
                chest.put("x", String.valueOf(x));
                chest.put("z", String.valueOf(z));
                chest.put("y", String.valueOf(y));

                chests.add(chest);
                mapper.writeValue(file, chests);

            } catch (Exception e) {
                e.printStackTrace();
            }

    }



    public static void addPos(String kitname, double x, double z, double y) {
        try {

            double posX = (int) x;
            double posZ = (int) z;
            double posY = (int) y;

            ObjectMapper mapper = new ObjectMapper();
            File file = new File("autoportaldupe.json");

            List<Map<String, String>> chests = new ArrayList<>();
            if (file.exists()) {
                chests = mapper.readValue(file, List.class);
            }

            Map<String, String> chest = new HashMap<>();
            chest.put("kitname", kitname);
            chest.put("x", String.valueOf(x));
            chest.put("z", String.valueOf(z));
            chest.put("y", String.valueOf(y));

            chests.add(chest);
            mapper.writeValue(file, chests);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

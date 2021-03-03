package frc.robot.path;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PathDataModel {
    private final PathState[] pathStates;

    public static String readFromInputStream(String jsonFile) {
        try {
            StringBuilder builder = new StringBuilder();
            FileReader reader = new FileReader(jsonFile);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while((line = br.readLine()) != null) {
                builder.append(line).append("\n");
            }
            return builder.toString();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public PathDataModel(String json) {
        Gson gson = new Gson();
        TypeToken<PathState[]> typeToken = new TypeToken<>(){};

        pathStates = gson.fromJson(json, typeToken.getType());
    }

    public PathState[] getPathStates() {
        return pathStates;
    }
}

package frc.robot.path;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PathDataModel {
    private PathState[] pathStates;

    public PathDataModel(String json) {
        Gson gson = new Gson();
        TypeToken<PathState[]> typeToken = new TypeToken<>(){};

        pathStates = gson.fromJson(json, typeToken.getType());

    }
}

package service;

import config.RouteMap;

public class Util {

    public static String getFullPath(String destination) {
        return "http://localhost:8080" + RouteMap.PREFIX + "/base" + destination;
    }

}

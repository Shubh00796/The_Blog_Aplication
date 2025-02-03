package com.Personal.blogapplication.Utils;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Component;
import org.mapstruct.Named;  // Import MapStruct Named

@Component
public class RideMapperHelper {

    private static final GeometryFactory geometryFactory = new GeometryFactory();

    // This helper method converts a Coordinate to a Point
    @Named("coordinateToPoint")
    public static Point coordinateToPoint(Coordinate coordinate) {
        if (coordinate == null) {
            return null;
        }
        return geometryFactory.createPoint(coordinate);
    }

    // This helper method converts a Point to a Coordinate
    @Named("pointToCoordinate")
    public static Coordinate pointToCoordinate(Point point) {
        if (point == null) {
            return null;
        }
        return point.getCoordinate();
    }
}

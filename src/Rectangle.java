/**
 * Represents a rectangle in a 2D space with methods to check containment, intersection, and subdivision.
 * This class is used for spatial calculations and quad-tree implementations.
 */
public class Rectangle {
    double x, y, width, height; // Center coordinates (x, y) and dimensions (width, height) of the rectangle
    double halfWidth, halfHeight;
    /**
     * Constructs a Rectangle object with specified center coordinates and dimensions.
     * @param x double, the x-coordinate of the rectangle's center.
     * @param y double, the y-coordinate of the rectangle's center.
     * @param width double, the width of the rectangle.
     * @param height double, the height of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.halfWidth = width / 2;
        this.halfHeight = height / 2;
    }

    /**
     * Checks if a given Place is contained within this rectangle.
     * @param place Place, the place to check for containment.
     * @return boolean, true if the place is within the rectangle, false otherwise.
     * Time Complexity: O(1).
     */
    public boolean isContains(Place place) {
        return (x - halfWidth <= place.x && place.x <= x + halfWidth
                && y - halfHeight <= place.y && place.y <= y + halfHeight);
    }

     /**
     * Checks if this rectangle intersects with another rectangle.
     * @param rectangle Rectangle, the other rectangle to check for intersection.
     * @return boolean, true if the rectangles intersect, false otherwise.
     * Time Complexity: O(1).
     */
    public boolean isIntersects(Rectangle rectangle) {
        return !(x - (width / 2) >= rectangle.x + (rectangle.width / 2) || rectangle.x - (rectangle.width / 2) >= x + (width / 2)
                || y - (height / 2) >= rectangle.y + (rectangle.height / 2) || rectangle.y - (rectangle.height / 2) >= y + (height / 2));
    }

    /**
     * Subdivides this rectangle into one of four quadrants and returns the new rectangle.
     * @param quadrant String, the quadrant to subdivide into ("topLeft", "topRight", "lowerLeft", "lowerRight").
     * @return Rectangle, the subdivided rectangle, or null if an invalid quadrant is specified.
     * Time Complexity: O(1).
     */
    public Rectangle subdivide(String quadrant) {
        double newX = x;
        double newY = y;
        double newWidth = width / 2;
        double newHeight = height / 2;

        switch (quadrant) {
            case "topLeft":
                newX = x - newWidth / 2;
                newY = y - newHeight / 2;
                break;

            case "topRight":
                newX = x + newWidth / 2;
                newY = y - newHeight / 2;
                break;

            case "lowerLeft":
                newX = x - newWidth / 2;
                newY = y + newHeight / 2;
                break;

            case "lowerRight":
                newX = x + newWidth / 2;
                newY = y + newHeight / 2;
                break;

            default:
                return null;
        }
        return new Rectangle(newX, newY, newWidth, newHeight);
    }

    /**
     * Provides a string representation of the rectangle's boundaries.
     * @return String, a string representation of the rectangle showing its boundaries.
     * Time Complexity: O(1).
     */
    public String toString() {
        return "boundary = {" +
                "x = " + x + ", " +
                "y = " + y + ", " +
                "width = " + width + ", " +
                "height = " + height + "}";
    }
}
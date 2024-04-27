public class Rectangle {
    double x, y, width, height;

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public boolean isContains(Place place) {
        return (x - (width / 2) <= place.x && place.x < x + (width / 2)
                && y - (height / 2) <= place.y && place.y < y + (height / 2));
    }
    

    public boolean isIntersects(Rectangle rectangle) {
        return !(x - (width / 2) >= rectangle.x + (rectangle.width / 2) || rectangle.x - (rectangle.width / 2) >= x + (width / 2)
                || y - (height / 2) >= rectangle.y + (rectangle.height / 2) || rectangle.y - (rectangle.height / 2) >= y + (height / 2));
    }

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

    public String toString() {
        return "boundary = {" +
                "x = " + x + ", " +
                "y = " + y + ", " +
                "width = " + width + ", " +
                "height = " + height + ", ";
    }
}
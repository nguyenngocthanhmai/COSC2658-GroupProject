public class Rectangle {
    double x, y, width, height, left, right, top, bottom;

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.left = x - (width / 2);
        this.right = x + (width / 2);
        this.top = y - (height / 2);
        this.bottom = y + (height / 2);
    }

    public boolean isContains(Place place) {
        return (this.left <= place.point.x && place.point.x <= this.right
                && this.top <= place.point.y && place.point.y <= this.bottom);
    }

    public boolean isIntersects(Rectangle rectangle) {
        return !(this.right < rectangle.left || rectangle.right < this.left ||
                this.bottom < rectangle.top || rectangle.bottom < this.top);
    }

    public Rectangle subdivide(String quadrant) {
        switch (quadrant) {
            case "topLeft":
                return new Rectangle(this.x - this.width / 4, this.y - this.height / 4, this.width / 2, this.height / 2);
            case "topRight":
                return new Rectangle(this.x + this.width / 4, this.y - this.height / 4, this.width / 2, this.height / 2);
            case "lowerLeft":
                return new Rectangle(this.x - this.width / 4, this.y + this.height / 4, this.width / 2, this.height / 2);
            case "lowerRight":
                return new Rectangle(this.x + this.width / 4, this.y + this.height / 4, this.width / 2, this.height / 2);
            default:
                return null;
        }

        // double newX = x;
        // double newY = y;
        // double newWidth = width / 2;
        // double newHeight = height / 2;

        // switch (quadrant) {
        //     case "topLeft":
        //         newX = x - newWidth / 2;
        //         newY = y - newHeight / 2;
        //         break;

        //     case "topRight":
        //         newX = x + newWidth / 2;
        //         newY = y - newHeight / 2;
        //         break;

        //     case "lowerLeft":
        //         newX = x - newWidth / 2;
        //         newY = y + newHeight / 2;
        //         break;

        //     case "lowerRight":
        //         newX = x + newWidth / 2;
        //         newY = y + newHeight / 2;
        //         break;

        //     default:
        //         return null;
        // }
        // return new Rectangle(newX, newY, newWidth, newHeight);
    }

    public String toString() {
        return "boundary = {" +
                "x = " + x + ", " +
                "y = " + y + ", " +
                "width = " + width + ", " +
                "height = " + height + ", ";
    }
}

public class Place {
    ArrayList<ServiceType> service;
    double x;
    double y;

    public Place(ArrayList<ServiceType> service, double x, double y) {
        this.service = service;
        this.x = x;
        this.y = y;
    }

    public ArrayList<ServiceType> getService() {
        return service;
    }

    // not implement yet
    public boolean editServices() {
        return false;
    }

    // not implement yet
    public boolean removeService() {
        return false;
    }

    public boolean addService() {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Place: service = ");
        for (int i =0; i<service.size(); i++){
            sb.append(service.get(i));
            if (i != service.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(", x = ").append(x);
        sb.append(", y = ").append(y);
        return sb.toString();
    }

    public boolean compare(double a, double b) {
        if (Double.compare(a, x) == 0 && Double.compare(b, y) == 0) {
            return true;
        }
        return false;
    }
}
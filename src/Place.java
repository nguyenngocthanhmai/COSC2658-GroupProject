public class Place {
    ArrayList<ServiceType> service;
    double x;
    double y;

    public Place(ArrayList<ServiceType> service, double x, double y) {
        this.service = service;
        this.x = x;
        this.y = y;
    }

    public void printServices(){
        for (int i = 0; i < service.size(); i++) {
            System.out.println(i + ". " + service.get(i));
        }
    }

    public ServiceType getServiceByIndex(int index) {
        return service.get(index);
    }

    public boolean removeService(ServiceType serviceType) {
        if (!hasService(serviceType)) {
            System.out.println("Service not found!");
            return false;
        }

        if (service.size() == 1) {
            System.out.println("Can't remove the last service!");
            return false;
        }
        return service.remove(serviceType);
    }

    public boolean addService(ServiceType serviceType) {
        if (hasService(serviceType)) {
            System.out.println("Service already exists!");
            return false;
        }
        return service.insert(serviceType);
    }

    private boolean hasService(ServiceType serviceType) {
        return service.contains(serviceType);
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
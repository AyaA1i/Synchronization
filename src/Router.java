class Router {
    boolean[] Connected;
    int MaxConnections;

    Router(int MaxConnected) {
        MaxConnections = MaxConnected;
        Connected = new boolean[MaxConnections];
    }

    public int Connect() {
        for (int i = 0; i < MaxConnections; i++) {
            if (!Connected[i]) {
                Connected[i] = true;
                return i;
            }
        }
        return -1;
    }

    public void Disconnect(int ID) {
        Connected[ID] = false;
    }

}

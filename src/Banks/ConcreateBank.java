package Banks;

import Clients.Client;

public class ConcreateBank extends BasicBank {
    public ConcreateBank(String name) {
        super(name);
    }

    @Override
    public void addClient(Client client) {
        super.clients.put(client.getClientId(), client);
        System.out.println("Client added in TinkoffBank: " + client.getName());
    }

    @Override
    public void deleteClient(String clientId) {
        if (super.clients.containsKey(clientId)) {
            super.clients.remove(clientId);
            System.out.println("Client deleted from TinkoffBank: " + clientId);
        } else {
            System.out.println("Client not found in TinkoffBank");
        }
    }

    public void offerSpecialService(String clientId) {
        if (super.clients.containsKey(clientId)) {
            System.out.println("Offering a special service to client ID: " + clientId);
        } else {
            System.out.println("Client not found for special services");
        }
    }
}

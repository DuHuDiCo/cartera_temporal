package com.cartera_temp.cartera_temp.Dtos;

public class MessageResponse {

    private String detallesAdicionelesToSend;

    public MessageResponse(String detallesAdicionelesToSend) {
        this.detallesAdicionelesToSend = detallesAdicionelesToSend;
    }

    public String getDetallesAdicionelesToSend() {
        return detallesAdicionelesToSend;
    }

    public void setDetallesAdicionelesToSend(String detallesAdicionelesToSend) {
        this.detallesAdicionelesToSend = detallesAdicionelesToSend;
    }
    
}

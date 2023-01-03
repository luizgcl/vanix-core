package com.vanix.core.application.entities.group.assignment;

public enum Assignment {

    AUTO("Automáticamente atribuído"),
    STAFF("Atribuído por um membro da equipe"),
    CONSOLE("Atribuído por meio do console");

    String message;

    Assignment(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}

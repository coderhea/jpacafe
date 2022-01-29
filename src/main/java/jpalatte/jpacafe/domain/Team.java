package jpalatte.jpacafe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Team {
    private String teamName;
    private String floor;

    protected Team() {
    }

    public Team(String teamName, String floor) {
        this.teamName = teamName;
        this.floor = floor;
    }
}

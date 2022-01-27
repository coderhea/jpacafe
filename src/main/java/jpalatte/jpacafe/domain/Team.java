package jpalatte.jpacafe.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Team {
    private String floor;
    private String teamName;

    protected Team() {} //자바 기본 생성자 x public

    public Team(String floor, String teamName) {
        this.floor = floor;
        this.teamName = teamName;
    }

}

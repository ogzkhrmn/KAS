/*
 * @author : Oguz Kahraman
 * @since : 26.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name = "speakers")
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "conference_id")
    private Conference conference;

    @NotNull
    private String name;

    @NotNull
    private String photoUrl;

    @NotNull
    private String topics;

}

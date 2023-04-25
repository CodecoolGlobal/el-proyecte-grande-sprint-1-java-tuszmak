package com.codecool.ecosampler.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "sample_data")
public class SampleData {
    @Id
    @SequenceGenerator(
            name = "user_id_sequens",
            sequenceName = "user_id_sequens",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_id_sequens")
    private Long id;

    @OneToOne(targetEntity = User.class)
    private User user;

    @OneToOne(targetEntity = Form.class)
    private Form form;

    @OneToMany
    private List<Answer> answers;
}

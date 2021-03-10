package com.phoebus.library.librarymicroservicepurchase.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class UserLibraryDTO {
    @NotNull
    @Size(min = 2)
    private String name;
    @NotNull
    @Min(2)
    private int age;
    @NotNull
    @Size(min = 8)
    private String phone;
    @NotNull
    @Size(min = 8)
    private String email;
    @NotNull
    @Size(max = 1)
    private String gender;
    @NotNull
    private String specificID;
}

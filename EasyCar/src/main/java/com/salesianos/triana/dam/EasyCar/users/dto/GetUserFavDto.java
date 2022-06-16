package com.salesianos.triana.dam.EasyCar.users.dto;

import com.salesianos.triana.dam.EasyCar.model.Vehiculo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserFavDto {

    private List<Vehiculo> favoritos;
}

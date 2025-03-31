package com.example.twitter.mapper;

import com.example.twitter.dto.ClientAuxDTO;
import com.example.twitter.dto.ClientDTO;
import com.example.twitter.entity.Client;
import com.example.twitter.user.model.ERole;
import com.example.twitter.user.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientAuxMapper {
    Client DTOToEntity (ClientAuxDTO clientAuxDTO);
    ClientAuxDTO EntityToDTO(Client client);
    default Role map(String value) {
        return Role.builder()
                .name(ERole.valueOf(value))
                .build();
    }

    default String map(Role value) {
        return value.getName().toString();
    }
}

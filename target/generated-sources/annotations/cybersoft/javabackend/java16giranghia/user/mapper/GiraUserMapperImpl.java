package cybersoft.javabackend.java16giranghia.user.mapper;

import cybersoft.javabackend.java16giranghia.user.dto.GiraUserDTO;
import cybersoft.javabackend.java16giranghia.user.dto.GiraUserDTO.GiraUserDTOBuilder;
import cybersoft.javabackend.java16giranghia.user.model.GiraUser;
import cybersoft.javabackend.java16giranghia.user.model.GiraUser.GiraUserBuilder;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-03T21:10:53+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class GiraUserMapperImpl implements GiraUserMapper {

    @Override
    public GiraUser toModel(GiraUserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        GiraUserBuilder<?, ?> giraUser = GiraUser.builder();

        giraUser.username( dto.getUsername() );
        giraUser.password( dto.getPassword() );
        giraUser.displayName( dto.getDisplayName() );
        giraUser.email( dto.getEmail() );
        giraUser.status( dto.getStatus() );

        return giraUser.build();
    }

    @Override
    public GiraUserDTO toDTO(GiraUser user) {
        if ( user == null ) {
            return null;
        }

        GiraUserDTOBuilder<?, ?> giraUserDTO = GiraUserDTO.builder();

        giraUserDTO.username( user.getUsername() );
        giraUserDTO.password( user.getPassword() );
        giraUserDTO.displayName( user.getDisplayName() );
        giraUserDTO.email( user.getEmail() );
        giraUserDTO.status( user.getStatus() );

        return giraUserDTO.build();
    }
}

package cybersoft.javabackend.java16giranghia.role.mapper;

import cybersoft.javabackend.java16giranghia.role.dto.GiraGroupDTO;
import cybersoft.javabackend.java16giranghia.role.dto.GiraGroupWithRoleDTO;
import cybersoft.javabackend.java16giranghia.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giranghia.role.model.GiraGroup;
import cybersoft.javabackend.java16giranghia.role.model.GiraGroup.GiraGroupBuilder;
import cybersoft.javabackend.java16giranghia.role.model.GiraRole;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-30T15:01:31+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class GiraGroupMapperImpl implements GiraGroupMapper {

    @Override
    public GiraGroupDTO toDTO(GiraGroup model) {
        if ( model == null ) {
            return null;
        }

        GiraGroupDTO giraGroupDTO = new GiraGroupDTO();

        giraGroupDTO.setId( model.getId() );
        giraGroupDTO.setCode( model.getCode() );
        giraGroupDTO.setDescription( model.getDescription() );

        return giraGroupDTO;
    }

    @Override
    public GiraGroup toModel(GiraGroupDTO dto) {
        if ( dto == null ) {
            return null;
        }

        GiraGroupBuilder<?, ?> giraGroup = GiraGroup.builder();

        giraGroup.id( dto.getId() );
        giraGroup.code( dto.getCode() );
        giraGroup.description( dto.getDescription() );

        return giraGroup.build();
    }

    @Override
    public GiraGroupWithRoleDTO toDtoWithRoles(GiraGroup modifiedGroup) {
        if ( modifiedGroup == null ) {
            return null;
        }

        GiraGroupWithRoleDTO giraGroupWithRoleDTO = new GiraGroupWithRoleDTO();

        giraGroupWithRoleDTO.setId( modifiedGroup.getId() );
        giraGroupWithRoleDTO.setCode( modifiedGroup.getCode() );
        giraGroupWithRoleDTO.setDescription( modifiedGroup.getDescription() );
        giraGroupWithRoleDTO.setRoles( giraRoleSetToGiraRoleDTOSet( modifiedGroup.getRoles() ) );

        return giraGroupWithRoleDTO;
    }

    protected GiraRoleDTO giraRoleToGiraRoleDTO(GiraRole giraRole) {
        if ( giraRole == null ) {
            return null;
        }

        GiraRoleDTO giraRoleDTO = new GiraRoleDTO();

        giraRoleDTO.setCode( giraRole.getCode() );
        giraRoleDTO.setDescription( giraRole.getDescription() );

        return giraRoleDTO;
    }

    protected Set<GiraRoleDTO> giraRoleSetToGiraRoleDTOSet(Set<GiraRole> set) {
        if ( set == null ) {
            return null;
        }

        Set<GiraRoleDTO> set1 = new HashSet<GiraRoleDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( GiraRole giraRole : set ) {
            set1.add( giraRoleToGiraRoleDTO( giraRole ) );
        }

        return set1;
    }
}

//package br.com.gabrielferreira.usuario.dto.factory;
//
//import br.com.gabrielferreira.usuario.dto.response.TelefoneResponseDTO;
//import br.com.gabrielferreira.usuario.entity.Telefone;
//
//import static br.com.gabrielferreira.usuario.dto.factory.TipoTelefoneDTOFactory.*;
//
//public class TelefoneDTOFactory {
//
//    private TelefoneDTOFactory(){}
//
//    public static TelefoneResponseDTO toTelefoneDto(Telefone telefone){
//        if(telefone != null){
//            return new TelefoneResponseDTO(telefone.getId(), telefone.getNumero(), telefone.getDdd(), telefone.getDescricao(), toTipoTelefoneDto(telefone.getTipoTelefone()),
//                    telefone.getCreatedAt(), telefone.getUpdatedAt());
//        }
//        return null;
//    }
//}

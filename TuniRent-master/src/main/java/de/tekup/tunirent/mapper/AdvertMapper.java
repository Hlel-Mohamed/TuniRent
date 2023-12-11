package de.tekup.tunirent.mapper;

import de.tekup.tunirent.dto.AdvertDTO;
import de.tekup.tunirent.dto.AdvertRequest;
import de.tekup.tunirent.model.Advert;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AdvertMapper<T> {
    public AdvertDTO convertToDTO(Advert entity) {
        AdvertDTO advertDTO = new AdvertDTO();
        BeanUtils.copyProperties(entity, advertDTO);
        advertDTO.setCreatorId(entity.getCreator().getId());
        return advertDTO;
    }
    public Advert convertToEntity(AdvertRequest advertRequest){
        Advert advert = new Advert();
        BeanUtils.copyProperties(advertRequest, advert);
        return advert;
    }
}

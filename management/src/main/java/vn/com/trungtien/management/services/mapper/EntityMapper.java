package vn.com.trungtien.management.services.mapper;

import java.util.List;

public interface EntityMapper<E, D> {
    D toDTO(E entity);
    E toEntity(D dto);
    List<D> toDTOS(List<E> entities);
    List<E> toEntities(List<D> dtos);

}

package mareczek100.musiccontests.infrastructure.database.repository;


import lombok.AllArgsConstructor;
import mareczek100.musiccontests.business.dao.HeadmasterRepositoryDAO;
import mareczek100.musiccontests.domain.Headmaster;
import mareczek100.musiccontests.infrastructure.database.entity.HeadmasterEntity;
import mareczek100.musiccontests.infrastructure.database.mapper.HeadmasterEntityMapper;
import mareczek100.musiccontests.infrastructure.database.repository.jpaRepository.HeadmasterJpaRepository;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
public class HeadmasterRepositoryImpl implements HeadmasterRepositoryDAO {

    private final HeadmasterJpaRepository headmasterJpaRepository;
    private final HeadmasterEntityMapper headmasterEntityMapper;


    @Override
    public Headmaster insertHeadmaster(Headmaster headmaster) {
        HeadmasterEntity headmasterEntity = headmasterEntityMapper.mapFromDomainToEntity(headmaster);
        HeadmasterEntity savedHeadmasterEntity = headmasterJpaRepository.saveAndFlush(headmasterEntity);
        return headmasterEntityMapper.mapFromEntityToDomain(savedHeadmasterEntity);
    }

    @Override
    public List<Headmaster> findAllHeadmasters() {
        List<HeadmasterEntity> headmasterEntityList = headmasterJpaRepository.findAll();
        return headmasterEntityList.stream()
                .map(headmasterEntityMapper::mapFromEntityToDomain)
                .toList();
    }

    @Override
    public Optional<Headmaster> findHeadmasterByEmail(String email) {
        return headmasterJpaRepository.findHeadmasterByEmail(email);
    }
}
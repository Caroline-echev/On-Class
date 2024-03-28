package com.bootcamp.onclass.domain.api.usecase;


import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.domain.api.ICapacityServicePort;
import com.bootcamp.onclass.domain.exception.DuplicateTechnologiesListException;
import com.bootcamp.onclass.domain.model.Capacity;
import com.bootcamp.onclass.domain.model.Technology;
import com.bootcamp.onclass.domain.spi.ICapacityPersistencePort;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CapacityUseCase implements ICapacityServicePort {
    private final ICapacityPersistencePort capacityPersistencePort;
    public CapacityUseCase(ICapacityPersistencePort capacityPersistencePort) {
        this.capacityPersistencePort = capacityPersistencePort;
    }
    @Override
    public Capacity addCapacity(Capacity capacity) {
        if(!validateDuplicateTechnologies(capacity.getTechnologies())){
            throw new DuplicateTechnologiesListException(Constants.DUPLICATE_TECHNOLOGIES_LIST_EXCEPTIN_MESSAGE);
        }
        return capacityPersistencePort.addCapacity(capacity);
    }

    private boolean validateDuplicateTechnologies(List<Technology> technologies) {
        Set<Long> technologyId = new HashSet<>();
        for (Technology technology : technologies) {
            if ( technologyId.contains( technology.getId() ) ) {
                return false;
            }
            technologyId.add( technology.getId() );
        }
        return true;

    }
}

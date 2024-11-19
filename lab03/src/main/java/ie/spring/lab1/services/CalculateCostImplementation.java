package ie.spring.lab1.services;

import ie.spring.lab1.repositories.weddings.Wedding;
import ie.spring.lab1.repositories.weddings.WeddingRepository;
import ie.spring.lab1.services.utilities.TidyMoney;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Optional;

@Slf4j
@Service
public class CalculateCostImplementation implements CalculateCost {

    private final WeddingRepository weddingRepository;

    @Value("${vat.rate}")
        private double tax_rate;


    @Autowired
    public CalculateCostImplementation(WeddingRepository weddingRepository) {
            this.weddingRepository = weddingRepository;
        }


    @PostConstruct
    public void init() {
        log.info("CalculateCostImplementation bean has been created.");
    }

    @PreDestroy
    public void destroy() {
        log.info("CalculateCostImplementation bean will be destroyed.");
    }

    @Override
    public double calculateWeddingCostExVat(String weddingId) throws Exception {
        Optional<Wedding> weddingOptional = weddingRepository.findById(weddingId);
        if (weddingOptional.isPresent()) {
            Wedding wedding = weddingOptional.get();
            int numberOfGuests = weddingRepository.getNumberOfGuests(weddingId);
            double costExVat = wedding.getCostPerGuest() * numberOfGuests;
            log.info("Cost excluding VAT for wedding {}: {}", weddingId, costExVat);
            return costExVat;
        }
        throw new Exception("Wedding with id " + weddingId + " not found.");
    }

    @Override
    public double calculateWeddingCostIncVat(String weddingId) throws Exception {
        double costExVat = this.calculateWeddingCostExVat(weddingId);
        double costIncVat = costExVat * (1 + tax_rate);
        double tidyCostIncVat = TidyMoney.twoDigits(costIncVat);
        log.info("Cost including VAT for wedding {}: {}", weddingId, tidyCostIncVat);
        return tidyCostIncVat;
    }
}
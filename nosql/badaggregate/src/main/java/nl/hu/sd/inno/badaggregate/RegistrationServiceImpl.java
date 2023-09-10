package nl.hu.sd.inno.badaggregate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    private RegistrationRepository registrations;

    public RegistrationServiceImpl(RegistrationRepository registrations) {
        this.registrations = registrations;
    }

    public void markPage(String registrationId, int page) {
        this.registrations.findById(registrationId).orElseThrow().setLastPage(page);
    }
}

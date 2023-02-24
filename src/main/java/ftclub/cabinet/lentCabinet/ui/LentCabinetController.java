package ftclub.cabinet.lentCabinet.ui;

import ftclub.cabinet.lentCabinet.application.LentCabinetService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LentCabinetController {
    private LentCabinetService lentCabinetService;

    LentCabinetController(LentCabinetService lentCabinetService) {
        this.lentCabinetService = lentCabinetService;
    }

    @GetMapping()
    public void lentCabinet(@Param("userId") int userId, @Param("cabinetId") int cabinetId) throws Exception {
        lentCabinetService.lentCabinet(userId, cabinetId);
    }
}

package ftclub.cabinet.lentCabinet.application;


import ftclub.cabinet.cabinet.domain.Cabinet;
import ftclub.cabinet.cabinet.domain.CabinetRepository;
import ftclub.cabinet.cabinetUsageHistroy.domain.LentPolicy;
import ftclub.cabinet.cabinetUsageHistroy.domain.LentService;
import ftclub.cabinet.cabinetUsageHistroy.domain.UsageHistory;
import ftclub.cabinet.cabinetUsageHistroy.domain.UsageHistoryRepository;
import ftclub.cabinet.user.domain.User;
import ftclub.cabinet.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LentCabinetService {
    private UserRepository userRepository;
    private CabinetRepository cabinetRepository;
    private UsageHistoryRepository usageHistoryRepository;
    private LentService lentService;
    private LentPolicy lentPolicy;
    LentCabinetService(UserRepository userRepository, CabinetRepository cabinetRepository, UsageHistoryRepository usageHistoryRepository, LentService lentService, LentPolicy lentPolicy) {
        this.userRepository = userRepository;
        this.cabinetRepository = cabinetRepository;
        this.usageHistoryRepository = usageHistoryRepository;
        this.lentService = lentService;
    }
    // interface 도입
    // 팩터리 패턴 도입
    //  유저가 만들건지? 캐비넷이 만들건지?
    // 유저가 만드는게 자연스럽고, 캐비넷은 여러 우저를 가지고 있으니까. 결론은 유저가 팩토리 역할 하는게 자연스러울듯?
    //
    @Transactional
    public void lentCabinet2(int userId, int cabinetId) throws Exception {
        User user = this.userRepository.findById(userId).orElseThrow(()->new RuntimeException());
        Cabinet cabinet = this.cabinetRepository.findById(cabinetId).orElseThrow(()-> new RuntimeException());
        int lentCount = this.usageHistoryRepository.countByCabinetIdAndReturn_time(cabinetId, null);//
        // {userHistroy, cabinetStatus, ...}
        UsageHistory usageHistory = user.usageHistoryFactory(cabinet, lentCount, lentPolicy).orElseThrow(()-> new RuntimeException());
        if (usageHistory != null) { // lent 가능
            usageHistoryRepository.save(usageHistory);
            // 이벤트 혹은 인터페이스 방식 혹은 도메인 서비스 방식
            // 해야할 일: 상태 바꿔도 되는지 정책 검증 & 상태 변경 -> 이 작업을 누가, 어떻게 할건지?
            lentService.ChangeStatus(cabinet, user, lentCount + 1);
//            user.makeStatusInvalid();
//            if (lentPolicy.changeCabinetStatusCheck(cabinet, lentCount + 1)) cabinet.makeTypeUnavailable();
        } else { // lent 불가능
            throw new Exception('렌트불가능');
        }
    }

    @Transactional
    public void lentCabinet(int userId, int cabinetId) throws Exception {
        User user = this.userRepository.findById(userId).orElseThrow(()->new RuntimeException());
        Cabinet cabinet = this.cabinetRepository.findById(cabinetId).orElseThrow(()-> new RuntimeException());
        int lentCount = this.usageHistoryRepository.countByCabinetIdAndReturn_time(cabinetId, null);//
        // 메시지 말고 상태코드 등 다른 데이터도 필요. dto를 반환하게 바꾸자.
        String lentAvailableMsg = lentPolicy.LentPolicyCheck(user, cabinet, lentCount);
        if (lentAvailableMsg == null) { // lent 가능
            usageHistoryRepository.save(new UsageHistory(userId, cabinetId, null));
            lentService.ChangeStatus(cabinet, user, lentCount + 1);
//            user.makeStatusInvalid();
//            if (lentPolicy.changeCabinetStatusCheck(cabinet, lentCount + 1)) cabinet.makeTypeUnavailable();
        } else { // lent 불가능
            throw new Exception(lentAvailableMsg);
        }
    }


    @Transactional
    public void returnCabinet(int userId, int cabinetId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new RuntimeException());
        Cabinet cabinet = this.cabinetRepository.findById(cabinetId).orElseThrow(()-> new RuntimeException());
        UsageHistory usageHistory = usageHistoryRepository.findByCabinetIdAndUserIdAndReturn_time(cabinetId, userId, null);
        usageHistory.returnComplete();
        user.makeStatusValid();
        cabinet.makeTypeAvailable();1
    }
}

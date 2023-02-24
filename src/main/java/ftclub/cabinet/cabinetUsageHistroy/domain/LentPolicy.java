package ftclub.cabinet.cabinetUsageHistroy.domain;

import ftclub.cabinet.cabinet.domain.Cabinet;
import ftclub.cabinet.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LentPolicy {
    static private int publicCabinetMaxUser = 3;
    static private int privateCabinetMaxUser = 1;
    static private int[] validUserStatusList = new int[]{1, 2, 3};
    static private int[] inValidUserStatusList = new int[]{4, 5, 6};

    public String LentPolicyCheck(User user, Cabinet cabinet, int lentCount) {
        if (Arrays.asList(validUserStatusList).contains(user.getStatus())) {
            if (cabinet.getType() == 1) {
                return publicCabinetCheck(lentCount);
            } else {
                return privateCabinetCheck(lentCount);
            }
        } else {
            return "사물함을 빌릴 수 없는 유저입니다";
        }
    }

    private String publicCabinetCheck(int lentCount) {
        if (lentCount < publicCabinetMaxUser) return null;
        else return "공유 사물함이 꽉 찼습니다";
    }

    private String privateCabinetCheck(int lentCount) {
        if (lentCount < privateCabinetMaxUser) return null;
        else return "사물함이 꽉 찼습니다";
    }

    public boolean changeCabinetStatusCheck(Cabinet cabinet, int lentCount) {
        if (cabinet.getType() == 0 || lentCount == publicCabinetMaxUser) return true;
        else return false;
    }
}

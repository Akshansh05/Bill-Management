package Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceContext {
    private String deviceId;
    private boolean isUpiEnabled;
}

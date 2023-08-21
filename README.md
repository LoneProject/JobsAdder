# JobsAdder
**JobsAdder 플러그인**은 마인크래프트 서버에서 직업 시스템을 쉽게 개발할 수 있는 플러그인입니다.<br>
해당 플러그인은 현재 **스크립트**, **플러그인** 모두 간편한 API가 존재합니다.

<br>

# 플러그인 API
플러그인 API에는 직업 단계를 불러올 수 있는 API, 그리고 몇가지의 이벤트가 존재합니다.
```java
public class ExampleEvent implements Listener {

  @EventHandler
  public void onChangedJobStage(ChangedJobStageEvent event) {
    Player player = event.getPlayer();
    int stage = event.getStage();
    // 해당 이벤트는 플레이어의 직업 단계가 변경되었을 때의 발동되는 이벤트입니다.
  }

  @EventHandler
  public void onSelectedJob(SelectedJobEvent event) {
    Player player = event.getPlayer();
    int stage = event.getStage();
    // 해당 이벤트는 플레이어가 직업을 선택했을 때 발동되는 이벤트입니다.
  }

}
```

# JobsAdder
**JobsAdder 플러그인**은 마인크래프트 서버에서 직업 시스템을 쉽게 개발할 수 있는 플러그인입니다.<br>
해당 플러그인은 현재 **스크립트**, **플러그인** 모두 간편한 API가 존재합니다.<br>
각각의 API는 `README`를 아래로 내리면 예시 코드가 나와있습니다.<br>
해당 플러그인에는 `필수 플러그인`이 존재합니다.<br>

- Skript
- SVault

그리고, 스크립트 API, 플러그인 API와 관련하여 질문하실거나<br>
궁금하신게 있으시다면 디스코드 `lone64`으로 연락해주세요.

<br>

# 플러그인 API
플러그인 API에는 직업 단계를 불러올 수 있는 API, 그리고 몇가지의 이벤트가 존재합니다.<br>
예시 코드는 아래와 같습니다.
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

<br>

# 스크립트 API
스크립트 API에는 수 많은 이벤트들과 함수들이 존재합니다.<br>
예시 코드는 아래와 같습니다.

```javascript
# 플레이어의 직업 단계가 변경되었을 때 발동되는 이벤트
on changed job stage:
	message "&e%player's job% 직업&f의 &e%player's stage%단계&f를 &a해금&f하셨습니다!" to player
	stop

# 플레이어가 직업을 선택했을 때 발동되는 이벤트
on selected player job:
	broadcast "&e%player's name%&f님이 &a[%player's job% 직업]&f을 선택하셨습니다!"

# 직업을 소지하고 있는지 확인하는 조건문을 이용한 명령어 1
command /예시명령어1:
	trigger:
		if player has job:
			message "&a&l직업을 소지하고 있습니다!" to player
			stop
		message "&c&l직업을 소지하고 있지 않습니다!" to player

# 직업을 소지하고 있는지 확인하는 조건문을 이용한 명령어 2
command /예시명령어2:
	trigger:
		if player have job:
			message "&a&l직업을 소지하고 있습니다!" to player
			stop
		message "&c&l직업을 소지하고 있지 않습니다!" to player
```

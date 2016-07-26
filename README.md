# Android-SDK-Design-Level
При подключении модуля livetex к вашему приложению стоит обратить внимание на следующие моменты

1. Перенисти библиотеку app_sdk2.aar в папку libs 

2. В файле build.gradle основного приложения добавить 

	 ```adroid {
	    defaultConfig {
	        multiDexEnabled = true
	    }
	    packagingOptions {
	        exclude 'META-INF/NOTICE'
	        exclude 'META-INF/DEPENDENCIES'
	        exclude 'META-INF/LICENSE'
	    }
		}
		repositories {
		    flatDir {
		        dirs 'libs'
		    }
		}
		dependencies {
		    compile project(':livetex')
		}```
		
3. В манифесте основного приложения добавить в тег manifest  xmlns:tools="http://schemas.android.com/tools", 
			в тег application   tools:replace="android:icon"
			
4. В settings.gradle добавить ':livetex'

5. При инициализации в метод DataKeeper.setMainColor можно передать цвет темы с которой будет отображаться чат
6. Чтобы быстро поменять цвет основной темы приложения реализован метод DataKeeper.setMainColor(Context context, int color); 

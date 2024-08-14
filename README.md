# bus-web-api

## Table of Contents
* [Development Environment](#development-environment)
    * [Requirements](#requirements)
    * [Start](#start)
    * [Shutdown](#shutdown)
    * [Log](#log)
    * [DB](#db)
* [Code](#code)
    * [Architecture](#architecture)
    * [Module Dependency](#module-dependency)
    * [Library](#library)

## Development Environment
開発環境をdocker-composeで構築しています。

### Requirements
* Kotlin
* docker / docker-compose

### Start
```
docker compose up -d
```

### Shutdown
```
docker compose down
```

### Log
```
# ログの確認
docker compose logs

# ストリーミング
docker compose logs -f

# webサーバonly
docker compose logs web
docker compose logs -f web
```

### DB
マイグレーションツールの用意はありません。
<br>初回起動時に`sql/`以下にあるSQLファイルが実行されます。

## Code
### Architecture
```
.
├──src/main/kotlin/com.example.bus_web_api ----> application core codes
│   ├── BusData.kt   
│   ├── BusController.kt  
│   ├── BusService.kt
│   ├── BusRepository.kt
│   └── BusWebApiApplication.kt
│
└── sql  ----> DB definition master
```

#### BusData.kt
データ・モノの表現やその振る舞いを記述しています。

#### BusController.kt
HTTPリクエストのハンドラを実装するクラスです。
リクエストからパラメータを読み取り、エンドポイントに応じた処理を行ってレスポンスを返します。

#### BusService.kt
Respositoryのメソッドを実行します。

#### BusRepository.tk
DBなど外部モジュールへアクセスし、データの保存・取得・更新などの処理を実装します。

#### BusWebApiApplication.kt
アプリケーションのmain関数を実装します。

### Module Dependency
![モジュールの依存関係](doc/module_dependency.jpg)
### Library
* Spring Framework ([ドキュメント](https://docs.spring.io/spring-framework/reference/index.html))
* Encode
    * kotlinx.serialization ([ドキュメント](https://kotlinlang.org/docs/serialization.html))

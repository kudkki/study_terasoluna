# Terasoluna training

[はじめてのSpring MVCアプリケーション](http://terasolunaorg.github.io/guideline/current/ja/Overview/FirstApplication.html)

Terasolunaパッケージ無しSpringFrameworkでのデモ

基本的に元記事のとおりすすめるが、元記事のとおりできないところを
差分だけ以下に記述していくこととする



## 「2.3.1. 検証環境」~「2.3.3. サーバーを起動する」

### ビルドセット

https://start.spring.io/

- Gradle
- Java
- Spring Boot 3.1.3
- ProjectMetadata
  - com.example
  - helloworld
  - helloworld
  - Demo project for Spring Boot
  - com.example.helloworld
  - Jar
  - 17
- Dependenciesはbuild.gradle参照

### Spring MVCの設定

コンポーネントスキャンとしては `spring-mvc.xml` は今回つかわない
代わりに `HelloworldApplication.java` があれば問題ないはず


### HTMLテンプレート

JSPは使わないThymeleafの方が簡便だしポピュラーなので

## 「2.3.4. エコーアプリケーションの作成」

- `echo/index`はじめJSP使わずThymeleafで、メソッドはPOSTままでいく

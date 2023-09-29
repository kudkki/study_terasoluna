# Terasoluna training

今回はこれとにかく長い

[11.1. チュートリアル(Todoアプリケーション)](http://terasolunaorg.github.io/guideline/current/ja/Tutorial/TutorialTodo.html)

まずはJavaオンメモリだけでデータ使う


参考URL

- https://spring.pleiades.io/guides/gs/accessing-data-jpa/
- https://spring.pleiades.io/guides/gs/handling-form-submission/
- https://qiita.com/NagaokaKenichi/items/c6d1b76090ef5ef39482
- https://miruraku.com/java/thymeleaf/if/~
- https://miruraku.com/java/thymeleaf/loop/01~
- https://stackoverflow.com/questions/32087469/the-attribute-readonly-is-undefined-for-the-annotation-type-transactional
- https://github.com/terasolunaorg/tutorial-apps/tree/release/5.8.1.RELEASE
ここにチュートリアルのサンプルアプリケーションが詰まってるので、参考にしたらいい



以下要修正

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

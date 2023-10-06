# study_terasoluna

bcmアプリを理解できるだけの素養が無いので
写経などで少し基礎勉強を追加する

terasoluna使うとややこしいからそれぞれSpringFrameworkを直に使ったバージョンで  
チュートリアルこなす

## 共通環境

- Gradle
- IntelliJ
- Java17

## デモ

各ディレクトリREADMEへ本家チュートリアルとの差分など結果レポート

- [2.3. はじめてのSpring MVCアプリケーション](http://terasolunaorg.github.io/guideline/current/ja/Overview/FirstApplication.html)
    - [helloworld/](https://github.com/kudkki/study_terasoluna/tree/main/helloworld)
 

## メモ bcm前学習メニュー

http://terasolunaorg.github.io/guideline/current/ja/Introduction/Introduction.html

基本的にイントロダクションに沿った進め方を想定するが
さらにbcmへ入門することを前提とした必要そうな項目をとっている

- 2.3. はじめてのSpring MVCアプリケーション チュートリアル
    - 2.4. アプリケーションのレイヤ化　座学
- 11.1. チュートリアル(Todoアプリケーション)　チュートリアル
    - 3. アプリケーション開発　座学長い
    - （あくまでbcmを目指すのでcoomon.codeGenを除いて、infrastructure.handler/mapperやapplication.exceptionもcom.test...controller/repositoryも倣う感じに作りこむ）
- 11.2. チュートリアル(Todoアプリケーション REST編)　チュートリアル
    - 5.1. RESTful Web Service　座学
 
以降は、RESTful Todoアプリケーションをカスタムすることで学習する

- 4.2. 例外ハンドリング　座学＆実践（Terasoluna資料はxmlベースでやってるからそのままは難しい、ErrorをハンドルしてException処理する機能を追加する）
    - 【Spring】@RestControllerAdvice を使ってREST APIのエラーハンドラを作成する https://sebenkyo.com/2020/08/02/post-1260/#toc_id_3
    - Spring Boot で 404 Not Found などのエラーが発生した際の表示をカスタマイズする https://qiita.com/niwasawa/items/f3479ef16efa488039fb

- 6.3. データベースアクセス（JPA編）（Optional用に、JPA用いてページネーション機能を追加する）
    - 【Optional入門】Javaでnullを扱うベストプラクティスのご紹介 https://www.bold.ne.jp/engineer-club/java-optional 
    - ページネーション（実践） http://terasolunaorg.github.io/guideline/current/ja/ArchitectureInDetail/WebApplicationDetail/Pagination.html#dataaccessjpahowtousepageableobject
    - Spring Data JPA - リファレンスドキュメント （座学） https://spring.pleiades.io/spring-data/jpa/docs/current/reference/html/

- 10. 単体テスト
  10.1. 単体テスト概要
  10.2. 単体テストの実装
  10.3. 単体テストの実行

- JUnit 5 ユーザガイド https://udzuki.jp/public/junit5-user-guide-ja/#writing-tests-annotations
使ってるアノテーションはTest/BeforeEachのみだから使い方としてはリファレンス見て使えれば十分そう
それよりテスト観点として何をどうテストするものを書けばいいのか、とかどう構成すればいいのか、とかが必要そう
    - assertThatの使い方バリエーション（JUnitのAssertJの使い方 https://confrage.jp/junit%E3%81%AEassertj%E3%81%AE%E4%BD%BF%E3%81%84%E6%96%B9/）
    - Controller側は普通にlocalhost宛にHTTPリクエスト送ってレスポンスをAssertする
    - Repository側はbcmそのままに習ってrepositoryクラスの各メソッドを、引数やデータ状態に応じた形のテストを通す（メソッドと検索成功・失敗・nullチェックなど、で3つくらいが平均ぽい）

- 12.2. ボイラープレートコードの排除(Lombok)
    - domain.modelでの使い方（）
    - application.modeでの使い方（）

---

以下はbcmに必要でもないが残りチュートリアルこなしてから
他ドキュメントも読み込んでいくと良さそうという程度の意味で

- 11.3. セッションチュートリアル
- 11.4. Spring Securityチュートリアル

## 教材サイト以外の参考情報

- [DAO/DTOパターン](https://kanda-it-school-kensyu.com/java-jdbc-contents/jj_ch04/jj_0402/)
- 教材でコードが無いcommonライブラリ等はこちらから探してつかう  
https://github.com/terasolunaorg?q=gfw&type=all&language=&sort=


### 以下参考

http://terasolunaorg.github.io/guideline/current/ja/Introduction/Introduction.html
```
時間がない場合、まずは

はじめてのSpring MVCアプリケーション
アプリケーションのレイヤ化
チュートリアル(Todoアプリケーション)
アプリケーション開発
チュートリアル(Todoアプリケーション)
入力チェック
を読むとよい。
```

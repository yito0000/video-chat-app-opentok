# video-chat-app-opentok
Video Chat Demo App proejct for OpenTok



### 準備

はじめにOpenTokのアカウントを用意します。以下のサイトからsign upします。

https://www.vonage.com/communications-apis/video/

アカウントを用意したらログインします。左側のメニューにある `Projects` をクリックして新しいプロジェクトを作成します。

作成すると `Project API Key` `Project Secret` が生成されています。これをコピーしてbackendの [`application.yml`](./backend/src/main/resources/application.yml) にある `api-key` `secret-key` をそれぞれ書き換えます。



### 起動方法

backendをビルドします。実行環境はjava11です。

```
./gradlew clean build
```

ビルドが完了したら起動します。(IDEでの起動でも大丈夫です)

```
./gradlew bootRun
```



frontendで必要なライブラリをインストールします。

```
npm install
```

起動します。

```
npm run serve
```

### 使い方

`https://localhost:8081/video-chat` で画面を開き、ビデオチャットの相手に画面に表示されるURLを共有し、開いてもらいます。

### その他
- `npm run build` して作成された `dist` にある各ファイル・ディレクトリを `backend/src/main/resources/static/` 配下に置くと `backend` のみでアプリとして起動できます。
どこかのサーバーにデプロイして使いたい場合は、この作業をした後に `backend` の階層で `./gradlew clean build` でビルドして、 `backend/build/libs` 配下に生成された `.jar` ファイルをデプロイすればJavaアプリとして実行できます。

- 必要最低限の動作確認しかしてません。

- ルームの情報はメモリ上に保持されます。

## License

See the [LICENSE](LICENSE.md) file for license rights and limitations (MIT).

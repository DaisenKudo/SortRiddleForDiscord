# SortRiddleForDiscord
SortRiddleゲームをDiscordで遊ぶためのBot

# 利用方法
1. Discord Botを作成する。右記の権限が必要です。「Send Messages」「Embed Links」「Read Message History」「Mention Everyone」
1. コンパイルする
1. /opt/settings.jsonをsrc/main/resources/setting_example.jsonのように書く。作成したBotsのTokenを記載してください。
1. 動かす。以下がおすすめです。
`nohup java -jar YOUR_JAR_FILE >YOUR_LOG_FILE 2>&1 &`

# コマンドリファレンス
-r : 1問出題します。すでに出題している場合は同じ問題を再度投稿します。問題がチャット上で流れてしまった場合に利用してください。  
-a (解答) : 解答を受け付けます。部分的に一致する場合は合っていた文字数を返します。  
-g : ギブアップを受け付けます。  
-c : リセットされます。ギブアップと同様の機能です。  
-h : ヘルプです。  

# ライセンスに関する表示
Discord4Jを利用しています。
https://github.com/Discord4J/Discord4J
  
Retrofitを利用しています。
https://square.github.io/retrofit/
